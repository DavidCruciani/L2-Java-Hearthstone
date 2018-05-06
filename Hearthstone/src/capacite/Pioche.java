package capacite;

import exception.HearthstoneException;
import plateau.Plateau;

/**
 * Pioche est une classe issue de la classe Capacite
 * Cette capacite permet au joueur qui la met en jeu de piocher une ou plusieurs carte
 * @author David Cruciani
 * @see Capacite
 */
public class Pioche extends Capacite {
		private int nombreCarte;

		public Pioche(String nom, String description, int nombreCarte) {
			super(nom, description);
			this.nombreCarte=nombreCarte;
		}
		
		public void executerAction(Object cible) {
			
		}
		
		public void executerEffetDebutTour() {
			
		}

		public void executerEffetDisparition(Object cible) {
		
		}

		public void executerEffetFinTour() {
		
		}

		public void executerEffetMiseEnJeu(Object cible) {
			for(int i=1;i<=nombreCarte;i++) {
				try {
					Plateau.plateau().getJoueurCourant().piocher();
				}
				catch(HearthstoneException e) {
					e.printStackTrace();
				}
			}
			setDejaUtilise(true);
		}

}
