package menu;

import carte.ICarte;
import exception.CapaciteException;
import exception.HearthstoneException;
import plateau.Plateau;
import menu.Console;

public class InterfaceUtiliserCarte extends Interface{
	public final static Console es = new Console();

	public InterfaceUtiliserCarte(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Utiliser une carte en jeu";
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Object o) throws Exception {
		ICarte carte = null;
		Object cible = null;
		//while( carte == null){
			System.out.println("Quelle carte jouer ? (un bout de son nom)");
			String choix = es.readLine();
			try {
				carte = Plateau.plateau().getJoueurCourant().getCarteEnJeu(choix);
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
						System.out.println("Quelle carte attaquer ? (un bout de son nom)");
						choix = es.readLine();
						try {
							cible = Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getCarteEnJeu(choix);
							
						}
						catch(HearthstoneException f)
						{
							System.out.println(f.getMessage());
						}
					//}
				}
				try {
					Plateau.plateau().getJoueurCourant().utiliserCarte(carte, cible);
				}
				catch(HearthstoneException | CapaciteException e) {
					System.out.println(e.getMessage());
				}
			}
			catch(HearthstoneException e)
			{
				System.out.println(e.getMessage());
			}
		//}
	}

}
