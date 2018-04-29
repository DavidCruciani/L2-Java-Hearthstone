package capacite;

import carte.Serviteur;
import plateau.Plateau;

/**
 * ImageMirroir est une classe issue de InvocationServiteur
 * invoque deux serviteurs 
 * @author David Cruciani
 * @see InvocationServiteur
 */
public class ImageMirroir extends InvocationServiteur {
	public ImageMirroir(String nom, String description, Serviteur invocation) {
		super(nom,description, new Serviteur("Mirroir","",plateau.getJoueurCourant() ,0,2, new Provocation() ) );
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
