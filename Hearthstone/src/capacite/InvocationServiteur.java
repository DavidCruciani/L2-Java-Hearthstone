package capacite;

import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;
import plateau.Plateau;

/**
 * InvocationServiteur classe issue de Capacite
 * invoque un serviteur sur le plateau
 * @author David Cruciani
 * @see Capacite
 */
public class InvocationServiteur extends Capacite {
	private Serviteur invocation;
	
	public InvocationServiteur(String nom, String description, Serviteur invocation) {
		super(nom, description);
		this.invocation=invocation;
	}
	
	public Serviteur getInvocation() {
		return this.invocation;
	}
	
	public void executerAction(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'action à executer");
		
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
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * Invoque un serviteur au joueur courant
	 * @throws HearthstoneException 
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		Plateau.plateau().getJoueurCourant().getJeu().add(this.getInvocation());
	}

}
