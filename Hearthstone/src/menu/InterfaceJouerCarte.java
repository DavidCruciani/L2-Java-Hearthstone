package menu;

import plateau.Plateau;
import capacite.MarqueChasseur;
import carte.ICarte;
import exception.HearthstoneException;
import menu.Console;


public class InterfaceJouerCarte extends Interface{

	public final static Console es = new Console();
	public InterfaceJouerCarte(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Jouer une carte de ta main";
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Object o) throws Exception {
		
		Object cible = null;
		ICarte carte = null;
		while( carte == null)
		{
			System.out.println("Quelle carte jouer ? (un bout de son nom)");
			String choix = es.readLine();
			try {
				carte = Plateau.plateau().getJoueurCourant().getCarteEnMain(choix);
				System.out.println(carte.toString());
			}
			catch(HearthstoneException e)
			{
				System.out.println(e.getMessage());
			}
		}
		if(carte.getCapacite() instanceof MarqueChasseur) {
			application.Main.ihm.interagir(carte, carte);
		}
		else {
			try {
				
				Plateau.plateau().getJoueurCourant().jouerCarte(carte);
			}
			catch (HearthstoneException | IllegalArgumentException e){
				int ent_cible = 0;
				while (ent_cible != 1 && ent_cible != 2)
				{
					System.out.println("Quelle est votre cible ?\n");
					System.out.println("1. Le héros\n");
					System.out.println("2. Une autre carte\n");
					ent_cible = es.readInt();
				}
				if (ent_cible == 1)
				{
					cible = Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getHeros();
				}
				else {
					//carte = null;
					//while (carte == null) {
						System.out.println("Quelle carte visez-vous ?");
						String choix = es.readLine();
						try {
							cible = Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getCarteEnJeu(choix);
							System.out.println(cible.toString());
						}
						catch(HearthstoneException f)
						{
							System.out.println(f.getMessage());
						}
					//}
				}
				Plateau.plateau().getJoueurCourant().jouerCarte(carte, cible);
			}
		}
	}
}
