package menu;

import plateau.Plateau;
import capacite.Charge;
import capacite.MarqueChasseur;
import carte.ICarte;
import exception.CapaciteException;
import exception.HearthstoneException;
import menu.Console;


public class InterfaceJouerCarte extends Interface{

	public final static Console es = new Console();
	public InterfaceJouerCarte(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Mettre une carte en jeu";
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Object o) throws Exception {
		
		Object cible = null;
		ICarte carte = null;
			System.out.println("Quelle carte jouer ? (un bout de son nom)");
			String choix = es.readLine();
			try {
				carte = Plateau.plateau().getJoueurCourant().getCarteEnMain(choix);
				//System.out.println(carte.toString());
			}
			catch(HearthstoneException e)
			{
				System.out.println(e.getMessage());
			}
		
		if(carte.getCapacite() instanceof MarqueChasseur) {
			//System.out.println("on y est____ ou pas... version MC :P");
			application.Main.ihm.interagir(carte, carte);
		}
		else if (carte.getCapacite() instanceof Charge ) {
			//System.out.println("on y est____ ou pas... :P");
			application.Main.ihm.interagir(carte, carte);
		}
		else {
			try {
				
				Plateau.plateau().getJoueurCourant().jouerCarte(carte);
			}
			catch(HearthstoneException e) {
				System.out.println(e.getMessage());
			}
			catch (IllegalArgumentException e){
				int ent_cible = 0;
				
					System.out.println("Quelle est votre cible ?\n");
					System.out.println("1. Le h�ros\n");
					System.out.println("2. Une autre carte\n");
					ent_cible = es.readInt();
				
				if (ent_cible == 1)
				{
					cible = Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getHeros();
				}
				else {
					//carte = null;
					//while (carte == null) {
						System.out.println("Quelle carte visez-vous ?");
						choix = es.readLine();
						try {
							cible = Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getCarteEnJeu(choix);
							//System.out.println(cible.toString());
						}
						catch(HearthstoneException f)
						{
							System.out.println(f.getMessage());
						}
					//}
				}
				try {
				Plateau.plateau().getJoueurCourant().jouerCarte(carte, cible);
				}
				catch(HearthstoneException | CapaciteException g) {
					System.out.println(g.getMessage());
				}
			}
		}
	}
}
