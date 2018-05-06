package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import heros.Heros;
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
	private String carteCible; 
	
	public EffetPermanent(String nom, String description, int vieBonus, int attaqueBonus) {
		super(nom, description);
		this.vieBonus=vieBonus;
		this.attaqueBonus=attaqueBonus;
	}
	
	public void setCarteCible(String cible) {
		this.carteCible=cible;
	}
	
	public String getCarteCible() {
		return this.carteCible;
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		
	}
	
	public void executerEffetDebutTour() {
		
	}

	/**
	 * Lorsque la carte possedant cette capacite disparait, le bonus disparait aussi
	 * il faut enlever alors les bonus ajoute a la carte qui les a eux
	 * @throws HearthstoneException 
	 */
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
			throw new IllegalArgumentException("Tu vise quoi la ? un oiseau ?");
		if(cible instanceof Heros) 
			throw new IllegalArgumentException("Tu dois viser un serviteur");
		if(cible instanceof Serviteur) {
			if(cible != this.getCarteCible()) {
				throw new IllegalArgumentException("Tu dois viser le même serviteur que toute a lheure");
			}
			((Serviteur) Plateau.plateau().getJoueurCourant().getCarteEnJeu( ( (String)cible) )).setPointDeVieBonus(-this.vieBonus);
			((Serviteur) Plateau.plateau().getJoueurCourant().getCarteEnJeu( ( (String)cible) )).setAttaqueBonus(-this.attaqueBonus);
		}
	}

	public void executerEffetFinTour() {
	
	}
	
	/**
	 * Lorsque la carte possedant cette capacite est mise en jeu, le bonus est mis sur un serviteur
	 * @throws HearthstoneException 
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
			throw new IllegalArgumentException("Tu vise quoi la ? un oiseau ?");
		if(cible instanceof Heros) 
			throw new IllegalArgumentException("Tu dois viser un serviteur");
		if(cible instanceof Serviteur) {
			setCarteCible( ( (String)cible ) );
			((Serviteur) Plateau.plateau().getJoueurCourant().getCarteEnJeu( ( (String)cible) )).setPointDeVieBonus(this.vieBonus);
			((Serviteur) Plateau.plateau().getJoueurCourant().getCarteEnJeu( ( (String)cible) )).setAttaqueBonus(this.attaqueBonus);
		}
	}
	
}
