package capacite;

import carte.Serviteur;
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
	
	public void executerAction(Object cible) {
		
	}

	public void executerEffetDebutTour() {
	
	}

	public void executerEffetDisparition(Object cible) {
	
	}

	public void executerEffetFinTour() {
	
	}

	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * Invoque un serviteur au joueur courant
	 */
	public void executerEffetMiseEnJeu(Object cible) {
		plateau.getJoueurCourant().getJeu().add(this.getInvocation());
	}
}
