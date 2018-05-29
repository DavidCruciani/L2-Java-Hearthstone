package capacite;

import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

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

	public void executerAction(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'effet d'action");
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

	public void executerEffetMiseEnJeu(Object cible) throws CapaciteException, HearthstoneException {
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		
		Heros hero=Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getHeros();
		
		if(!(hero instanceof Heros))
			throw new IllegalArgumentException("C'est pas un Heros que tu vise");
		((Heros)hero).perdreVie(degas);
		if( ((Heros)hero).estMort()) {
			Plateau.plateau().gagnePartie(Plateau.plateau().getJoueurCourant());
		}
		setDejaUtilise(true);
	}

	@Override
	public boolean getBesoinCible() {
		return false;
	}


}
