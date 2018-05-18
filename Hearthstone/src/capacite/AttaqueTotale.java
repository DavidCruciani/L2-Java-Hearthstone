package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

/**
 * AttaqueTotale est une classe issue de la classe Capacite
 * Cette capacite permet au serviteur cible d attaquer tout les serviteurs adverse pose sur le plateau
 * @author David Cruciani
 * @see Capacite
 */
public class AttaqueTotale extends Capacite {
	private int degas;
	
	public AttaqueTotale(String nom, String description, int degas) {
		super(nom, description);
		this.degas=degas;
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
			throw new IllegalArgumentException("C'est pas un Heros que tu vise");
		if(cible instanceof Heros) {
				throw new HearthstoneException("Tu dois choisir un serviteur");
		}
		for(ICarte carte : Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu() )
			((Serviteur) carte).estAttaquer(degas);
		
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
