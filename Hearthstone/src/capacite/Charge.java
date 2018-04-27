package capacite;

import carte.Serviteur;

public class Charge extends Capacite {

	public Charge() {
		super("Charge", "Permet de ne pas attendre");
	}

	
	public void executerAction(Object cible) {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if(!(cible instanceof Serviteur))
			throw new IllegalArgumentException("C'est pas un serviteur que tu vise");
		((Serviteur)cible).setAttendre(false);
		setDejaUtilise(true);
	}

	public void executerEffetDebutTour() {
	
	}

	public void executerEffetDisparition(Object cible) {
	
	}

	public void executerEffetFinTour() {
	
	}

	public void executerEffetMiseEnJeu(Object cible) {
		
	}
	
}
