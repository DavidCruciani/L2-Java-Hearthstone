package carte;

import capacite.ICapacite;
import exception.HearthstoneException;
import joueur.IJoueur;

/**
 * Sort est une classe issue de la classe Carte
 * @author David Cruciani
 * @see Carte
 */
public class Sort extends Carte {
	private ICapacite capacite;
	
	public Sort(String nom, int cout, IJoueur proprietaire, ICapacite capacite) {
		super(nom,cout,proprietaire);
		this.setCapacite(capacite);
	}
	
	public ICapacite getCapacite() {
		return this.capacite;
	}
	
	public void setCapacite(ICapacite capacite) {
		this.capacite = capacite;
	}
	
	public boolean disparait() {
		return true;
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		
	}
	
	public void executerEffetDebutTour(Object cible) {
		
	}
	
	public void executerEffetFinTour() {
		
	}

	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		this.capacite.executerEffetMiseEnJeu(cible);
	}

	public void executerEffetDisparition(Object cible) {
		
	}

	public String toString()
	{
		return "Sort [ " + super.toString() + ", Capacite " + this.getCapacite() + " ] \n";
	}	
	
}
