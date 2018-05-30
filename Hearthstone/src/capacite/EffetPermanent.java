package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;
import plateau.Plateau;

/**
 * EffetPermanent est une classe issue de la classe Capacite
 * Cette capacite permet au serviteur cible de percevoir un bonus d attaque et de vie
 * @author David Cruciani
 * @see Capacite
 */
public class EffetPermanent extends Capacite {
	private int vieBonus;
	private int attaqueBonus;
	
	public EffetPermanent(String nom, String description, int vieBonus, int attaqueBonus) {
		super(nom, description);
		this.vieBonus=vieBonus;
		this.attaqueBonus=attaqueBonus;
	}
	
	public void executerAction(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'action à executer");
	}
	
	public void executerEffetDebutTour() throws CapaciteException {
		throw new CapaciteException("Pas d'effet de début de tour");
		
	}

	/**
	 * Lorsque la carte possedant cette capacite disparait, le bonus disparait aussi
	 * il faut enlever alors les bonus a toute les autres cartes
	 * @throws HearthstoneException 
	 */
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		//System.out.println("tst3");
		for(ICarte carte : Plateau.plateau().getJoueurCourant().getJeu() ) {
			//System.out.println("tst");
			((Serviteur)carte).setPointDeVieBonus(((Serviteur)carte).getPointDeVie()-this.vieBonus);
			((Serviteur)carte).setAttaqueBonus(((Serviteur)carte).getAttaque()-this.attaqueBonus);
		}
	}

	public void executerEffetFinTour() throws CapaciteException {
		throw new CapaciteException("Pas d'effet de fin de tour");
	
	}
	
	/**
	 * Lorsque la carte possedant cette capacite est mise en jeu, le bonus est mis sur chaque serviteur en jeu
	 * @throws HearthstoneException 
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		for(ICarte carte : Plateau.plateau().getJoueurCourant().getJeu() ) {
			((Serviteur)carte).setPointDeVieBonus(this.vieBonus);
			((Serviteur)carte).setAttaqueBonus(this.attaqueBonus);
		}
		
	}

}
