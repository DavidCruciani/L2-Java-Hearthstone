package capacite;

import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;

/**
 * Charge est une classe issue de la classe Capacite
 * Cette capacite permet au serviteur cible de ne pas attendre pour attaquer lors de sa mise en jeu
 * @author David Cruciani
 * @see Capacite
 */

public class Charge extends Capacite {

	public Charge() {
		super("Charge", "Permet de ne pas attendre");
	}

	
	public void executerAction(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'action a effectuer");
		
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

	/**
	 * Enleve l attente d un serviteur en jeu 
	 * @throws HearthstoneException 
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(!(cible instanceof Serviteur))
			throw new IllegalArgumentException("C'est pas un serviteur que tu vise");
		if( !( ( (Serviteur) cible ).getAttendre() ) )
			throw new HearthstoneException("Le serviteur n'attend pas, utilise la charge sur un autre");
		((Serviteur)cible).setAttendre(false);
		setDejaUtilise(true);
	}

}
