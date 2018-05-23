package menu;

import carte.ICarte;
import exception.HearthstoneException;
import plateau.Plateau;

public class InterfaceUtiliserPouvoir extends Interface {

	public final static Console es = new Console();
	public InterfaceUtiliserPouvoir(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Utiliser un pouvoir";
	}

	@Override
	public boolean saisInteragir(String actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Plateau plateau) throws Exception {
		//ICarte carte = null;

		try {
			plateau.getJoueurCourant().utiliserPouvoir(null);
		}
		catch(IllegalArgumentException e) {
			int ent_cible = 0;
			Object cible = null;
			String choix;
			
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
				/*carte = null;
				while (carte == null) {*/
					System.out.println("Quelle carte visez-vous ?");
					choix = es.readLine();
					try {
						cible = plateau.getAdversaire(plateau.getJoueurCourant()).getCarteEnJeu(choix);
					}
					catch(HearthstoneException f)
					{
						System.out.println(f.getMessage());
					}
				//}
			}
			try {		
				plateau.getJoueurCourant().utiliserPouvoir(cible);
			}
			catch(HearthstoneException f) {
				System.out.println(f.getMessage());
			}
		}
		
		
		/*try {		
			plateau.getJoueurCourant().utiliserPouvoir(cible);
		}
		catch(HearthstoneException f) {
			System.out.println(f.getMessage());
		}*/
	}

}
