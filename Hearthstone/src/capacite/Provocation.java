package capacite;

import exception.CapaciteException;

/**
 * Provocation est une classe issue de la classe Capacite
 * Cette capacite permet au serviteur cible d etre cible par toute les attaques de l adversaire
 * @author David Cruciani
 * @see Capacite
 */
public class Provocation extends Capacite {

	public Provocation() {
		super("Provocation", "oblige l'adversaire a viser cette carte" );
	}

	public void executerAction(Object cible)  throws CapaciteException {
		throw new CapaciteException("Pas d'action a executer");
	
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

	public void executerEffetMiseEnJeu(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'effet de mise en jeu");
	}

	@Override
	public boolean getBesoinCible() {
		return false;
	}

}
