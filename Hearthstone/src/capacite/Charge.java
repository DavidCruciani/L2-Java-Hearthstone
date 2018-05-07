package capacite;

import carte.Serviteur;
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

	
	public void executerAction(Object cible) {
		
	}

	public void executerEffetDebutTour() {
	
	}

	public void executerEffetDisparition(Object cible) {
	
	}

	public void executerEffetFinTour() {
	
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
			throw new HearthstoneException("Le serviteur n'attend pas, utilise lacharge sur un autre");
		((Serviteur)cible).setAttendre(false);
		setDejaUtilise(true);
	}
	
}