package menu;

import plateau.Plateau;
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
	public boolean saisInteragir(String actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Plateau plateau) throws Exception {
		
		Object cible = null;
		ICarte carte = null;
		while( carte == null)
		{
			System.out.println("Quelle carte jouer ? (un bout de son nom)");
			String choix = es.readLine();
			try {
				carte = plateau.getJoueurCourant().getCarteEnMain(choix);
			}
			catch(HearthstoneException e)
			{
				System.out.println(e.getMessage());
			}
		}
		try {
			
			plateau.getJoueurCourant().jouerCarte(carte);
		}
		catch (HearthstoneException e){
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
				cible = plateau.getAdversaire(plateau.getJoueurCourant()).getHeros();
			}
			else {
				carte = null;
				while (carte == null) {
					System.out.println("Quelle carte visez-vous ?");
					String choix = es.readLine();
					try {
						cible = plateau.getAdversaire(plateau.getJoueurCourant()).getCarteEnJeu(choix);
					}
					catch(HearthstoneException f)
					{
						System.out.println(f.getMessage());
					}
				}
			}
			plateau.getJoueurCourant().jouerCarte(carte, cible);
		}
		
	}

}
