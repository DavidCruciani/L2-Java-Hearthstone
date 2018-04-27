package capacite;

import heros.Heros;

public class AttaqueHeros extends Capacite {

	private int degas;

	public AttaqueHeros(String nom, String description, int degas) {
		super(nom, description);
		this.degas=degas;
	}

	public void executerAction(Object cible) {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if(!(cible instanceof Heros))
			throw new IllegalArgumentException("C'est pas un Heros que tu vise");
		((Heros)cible).perdreVie(degas);
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
