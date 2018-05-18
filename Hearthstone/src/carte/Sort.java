package carte;

import capacite.ICapacite;
import exception.CapaciteException;
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
	
	public void executerAction(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'action a executer");
	}
	
	public void executerEffetDebutTour(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'effet de début de tour");
		
	}
	
	public void executerEffetFinTour() throws CapaciteException {
		throw new CapaciteException("Pas d'effet de fin de tour");		
	}

	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CapaciteException {
		this.capacite.executerEffetMiseEnJeu(cible);
	}

	public void executerEffetDisparition(Object cible) {
		
	}

	public String toString()
	{
		return "Sort [ " + super.toString() + ", Capacite " + this.getCapacite() + " ] \n";
	}	
	
}
