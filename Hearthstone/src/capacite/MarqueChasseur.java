package capacite;

import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;
import plateau.Plateau;

 /**
  * MarqueChasseur est une classe issue de Capacite
  * Met la vie d'un serviteur adverse en jeu a 1
  * @author David Cruciani
  * @see Capacite
  */
public class MarqueChasseur extends Capacite {
	public MarqueChasseur(String nom, String description) {
		super(nom,description);
	}
	
	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * Met la vie d'un serviteur adverse en jeu a 1
	 * @throws CapaciteException 
	 */
	public void executerAction(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'effet de mise en jeu");
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

	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if( !(cible instanceof Serviteur) ) 
			throw new HearthstoneException("Vise un serviteur");
		else {
			if(Plateau.plateau().getAdversaire( Plateau.plateau().getJoueurCourant() ).getJeu().contains( ( (Serviteur)cible) ) )
				((Serviteur) Plateau.plateau().getAdversaire( Plateau.plateau().getJoueurCourant() ).getCarteEnJeu( ((Serviteur) cible).getNom() )).setPointDeVie(1);
			else
				throw new HearthstoneException("Ta cible existe pas");
		}
	}

}
