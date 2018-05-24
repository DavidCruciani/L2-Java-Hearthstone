package capacite;

import carte.ICarte;
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
	public InvocationChien(String nom, String description) {
		super(nom,description, new Serviteur("Chiens",0,Plateau.plateau().getJoueurCourant() ,1,1, new Charge() ) );
	}
	
	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * invoque un serviteur au joueur courant en fonction du nombre de serviteur adverse en jeu
	 * @throws HearthstoneException 
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		 int nb_serviteurs=0;
		 for ( ICarte c : Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu())
		 {
		 	if(c instanceof Serviteur)
		 		nb_serviteurs++;
		 }
		 
		 for(int i=0;i<nb_serviteurs;i++)
		 {
		 	Serviteur serviteur = (Serviteur) getInvocation().clone();
		 	serviteur.setProprietaire(Plateau.plateau().getJoueurCourant());
		 	serviteur.setNom(serviteur.getNom() + " " + i+1);
		 	Plateau.plateau().getJoueurCourant().getJeu().add(serviteur);
	
}
	}
}
