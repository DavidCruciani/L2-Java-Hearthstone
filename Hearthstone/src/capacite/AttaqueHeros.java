package capacite;

import exception.CapaciteException;
import heros.Heros;

/**
 * AttaqueHeros est une classe issue de la classe Capacite
 * Cette capacite permet au serviteur cible d attaquer le Hero adverse
 * meme si celui ci possede une provocation en jeu
 * @author David Cruciani
 * @see Capacite
 */
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


}
