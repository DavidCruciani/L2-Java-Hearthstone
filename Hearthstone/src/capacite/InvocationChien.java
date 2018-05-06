package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import plateau.Plateau;
import capacite.Charge;

/**
 * InvocationChien est une classe issue de InvocationServiteur
 * invoque des chiens en fonction du nombre de serviteur adverse en jeu
 * @author David Cruciani
 * @see InvocationServiteur
 */
public class InvocationChien extends InvocationServiteur{
	public InvocationChien(String nom, String description, Serviteur invocation) {
		super(nom,description, new Serviteur("Chiens",0,Plateau.plateau().getJoueurCourant() ,1,1, new Charge() ) );
	}
	
	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * invoque un serviteur au joueur courant en fonction du nombre de serviteur adverse en jeu
	 * @throws HearthstoneException 
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if( Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant() ).getJeu().size() != 0 ) {
			for(int i=1;i<= Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant() ).getJeu().size();i++ ) {
				Plateau.plateau().getJoueurCourant().getJeu().add(this.getInvocation());
			}
		}
	}
}
