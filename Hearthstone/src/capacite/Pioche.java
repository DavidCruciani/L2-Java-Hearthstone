package capacite;

import exception.CapaciteException;
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
		
		public void executerAction(Object cible) throws CapaciteException {
			throw new CapaciteException("Pas d'action à effectuer");
			
		}
		
		public void executerEffetDebutTour() throws CapaciteException {
			throw new CapaciteException("Pas d'effet de début de tour");
			
		}

		public void executerEffetDisparition(Object cible) throws CapaciteException {
			throw new CapaciteException("Pas d'effet de disparition");
		
		}

		public void executerEffetFinTour() throws CapaciteException {
			throw new CapaciteException("Pas d'effet de fin de tour");
		
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
