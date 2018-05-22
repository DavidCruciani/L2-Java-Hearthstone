package carte;

import capacite.ICapacite;
import exception.CapaciteException;
import exception.HearthstoneException;
import joueur.IJoueur;

/**
 * Serviteur est une classe issue de la classe Carte
 * @author David Cruciani
 * @see Carte
 */
public class Serviteur extends Carte{
	private int attaque;
	private int pdv;
	private boolean attendre;
	private boolean peutJouer = true;
	private ICapacite capacite;
	
	public Serviteur(String nom, int cout, IJoueur proprietaire, int attaque, int pdv,ICapacite capacite) {
		super(nom,cout,proprietaire);
		this.setAttaque(attaque);
		this.setPointDeVie(pdv);
		attendre = true;
		this.setCapacite(capacite);
	}
	
	public int getAttaque() {
		return this.attaque;
	}
	public int getPointDeVie() {
		return this.pdv;
	}
	public boolean getAttendre() {
		return this.attendre;
	}
	public ICapacite getCapacite() {
		return this.capacite;
	}
	
	public void setAttaque(int attaque) {
		this.attaque=attaque;
	}
	public void setAttaqueBonus(int attaqueBonus) {
		this.attaque+=attaqueBonus;
	}
	public void setPointDeVie(int pdv) {
		this.pdv=pdv;
	}
	public void setPointDeVieBonus(int pdvBonus) {
		this.pdv+=pdvBonus;
	}
	public void setAttendre(boolean attendre) {
		this.attendre=attendre;
	}
	public void setCapacite(ICapacite capacite) {
		this.capacite = capacite;
	}
	/**
	 * @return true si la vie de la carte est inferieur ou egale a 0
	 */
	public boolean disparait() {
		return this.getPointDeVie()<=0;
	}
	
	/**
	 * 
	 * @param attaque
	 * 				degas que prend la carte lorsque elle est attaque
	 */
	public void estAttaquer(int attaque) {
		if(this.getPointDeVie()-attaque > 0)
			this.setPointDeVie(getAttaque()-attaque);
		else
			this.setPointDeVie(0);
	}
	
	public String toString()
	{
		return "Serviteur [ " + super.toString() + ", Capacite " + this.getCapacite() + ", Attaque = " + this.getAttaque() + ", Vie = " + 
				this.getPointDeVie() + " ] \n" ;
	}	
	
	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * La carte effectue l action que lui confere sa capacite
	 * si elle n est pas deja utilisee
	 * @throws CapaciteException 
	 */
	public void executerAction(Object cible) throws HearthstoneException, CapaciteException {
		if(this.getAttendre())
			throw new HearthstoneException("Faut attendre un tour");
		
		if(this.peutJouer)
			this.getCapacite().executerAction(cible);
		
		else
			throw new HearthstoneException("Deja joué");
		
		peutJouer=true;
	}
	
	public void executerAction() throws HearthstoneException, CapaciteException {
		if(this.getAttendre())
			throw new HearthstoneException("Faut attendre un tour");
		
		if(this.peutJouer)
			this.getCapacite().executerAction(this);
		
		else
			throw new HearthstoneException("Deja joué");
		
		peutJouer=true;
	}
	
	public void executerEffetDebutTour(Object cible) throws CapaciteException {
		this.getCapacite().executerEffetDebutTour();
	}
	
	public void executerEffetFinTour() throws CapaciteException {
		this.getCapacite().executerEffetFinTour();
	}

	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CapaciteException {
		this.getCapacite().executerEffetMiseEnJeu(cible);
	}

	public void executerEffetDisparition(Object cible) throws HearthstoneException, CapaciteException {
		this.getCapacite().executerEffetDisparition(cible);
	}
}
