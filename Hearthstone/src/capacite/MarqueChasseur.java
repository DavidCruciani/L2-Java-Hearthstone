package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import plateau.Plateau;

 /**
  * MarqueChasseur est une classe issue de Capacite
  * Met la vie d'un serviteur adverse en jeu a 1
  * @author David Cruciani
  * @see Capacite
  */
public class MarqueChasseur extends Capacite {
	public MarqueChasseur(String nom, String description) {
		super(nom,description);
	}
	
	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * Met la vie d'un serviteur adverse en jeu a 1
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if( !(cible instanceof Serviteur) ) 
			throw new HearthstoneException("Vise un serviteur");
		else {
			if(Plateau.plateau().getAdversaire( Plateau.plateau().getJoueurCourant() ).getJeu().contains( ( (Serviteur)cible) ) )
				((Serviteur) Plateau.plateau().getAdversaire( Plateau.plateau().getJoueurCourant() ).getCarteEnJeu( (String)cible )).setPointDeVie(1);
			else
				throw new HearthstoneException("Ta cible existe pas");
		}
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
