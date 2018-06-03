package menu;

import exception.CapaciteException;
import exception.HearthstoneException;
import plateau.Plateau;

public class InterfaceUtiliserPouvoir extends Interface {

	public final static Console es = new Console();
	public InterfaceUtiliserPouvoir(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Utiliser le pouvoir du hero";
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Object o) throws Exception {
		//ICarte carte = null;

		try {
			Plateau.plateau().getJoueurCourant().utiliserPouvoir(null);
		}
		catch(IllegalArgumentException e) {
			int ent_cible = 0;
			Object cible = null;
			String choix;
			
		
				System.out.println("Quelle est votre cible ?\n");
				System.out.println("1. Le héros\n");
				System.out.println("2. Une autre carte\n");
				ent_cible = es.readInt();
	
			if (ent_cible == 1)
			{
				cible = Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getHeros();
			}
			else {
				/*carte = null;
				while (carte == null) {*/
					System.out.println("Quelle carte visez-vous ?");
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
				Plateau.plateau().getJoueurCourant().utiliserPouvoir(cible);
			}
			catch(HearthstoneException | IllegalArgumentException | CapaciteException f) {
				System.out.println(f.getMessage());
			}
		}
	}

}
