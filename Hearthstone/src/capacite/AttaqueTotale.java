package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;
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

	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		for(int i=0;i<Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu().size();i++ ){
			((Serviteur) Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu().get(i)).estAttaquer(degas);
		}
		
		for(int i=1; i<Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu().size();i-- ){
			if( ((Serviteur) Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu().get(i)).disparait() ) {
				Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).perdreCarte(Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu().get(i));
			}
		}
		setDejaUtilise(true);
	}

	@Override
	public boolean getBesoinCible() {
		return false;
	}


}
