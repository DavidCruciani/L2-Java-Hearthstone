package capacite;

import carte.Serviteur;
import joueur.IJoueur;
import plateau.Plateau;

/**
 * ImageMirroir est une classe issue de InvocationServiteur
 * invoque deux serviteurs 
 * @author David Cruciani
 * @see InvocationServiteur
 */
public class ImageMirroir extends InvocationServiteur {
	public ImageMirroir(String nom, String description) {
		super(nom,description, new Serviteur("Mirroir",0,Plateau.plateau().getJoueurCourant() ,0,2, new Provocation() ) );
	}
	
	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * Invoque un serviteur au joueur courant
	 */
	public void executerEffetMiseEnJeu(Object cible) {
		IJoueur joueur_courant=Plateau.plateau().getJoueurCourant();
		
		Serviteur invoque1= new Serviteur("Serviteur de Jaina", 0 , joueur_courant , 0 , 2 , new Provocation());
		Serviteur invoque2= (Serviteur) invoque1.clone();
		
		joueur_courant.getJeu().add(invoque1);
		joueur_courant.getJeu().add(invoque2);
	}
}
