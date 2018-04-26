package carte;

import capacite.ICapacite;
import exception.HearthstoneException;
import joueur.IJoueur;

public class Serviteur extends Carte{
	private int attaque;
	private int pdv;
	private boolean attendre;
	private boolean peutJouer = true;
	private ICapacite capacite;
	
	public Serviteur(String nom, int cout, IJoueur proprietaire, int attaque, int pdv, boolean attendre, ICapacite capacite) {
		super(nom,cout,proprietaire);
		this.setAttaque(attaque);
		this.setPointDeVie(pdv);
		this.setAttendre(attendre);
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
	public void setPointDeVie(int pdv) {
		this.pdv=pdv;
	}
	public void setAttendre(boolean attendre) {
		this.attendre=attendre;
	}
	public void setCapacite(ICapacite capacite) {
		this.capacite = capacite;
	}
	
	public boolean disparait() {
		return this.getPointDeVie()>=0;
	}
	
	public void estAttaquer(int attaque) {
		if(this.getPointDeVie()-attaque > 0)
			this.setPointDeVie(getAttaque()-attaque);
		else
			this.setPointDeVie(0);
	}
	
	public String toString()
	{
		return " Serviteur [ " + super.toString() + "attaque = " + this.getAttaque() + " vie = " + 
				this.getPointDeVie() + " ] " ;
	}	
	
	public void executerAction(Object cible) throws HearthstoneException {
		if(this.getAttendre())
			throw new HearthstoneException("Faut attendre un tour");
		
		if(this.peutJouer)
			this.getCapacite().executerAction(cible);
		
		else
			throw new HearthstoneException("Deja joué");
		
		peutJouer=true;
	}
	
	public void executerEffetDebutTour(Object cible) {
		this.getCapacite().executerEffetDebutTour();
	}
	
	public void executerEffetFinTour() {
		this.getCapacite().executerEffetFinTour();
	}

	public void executerEffetDebutMiseEnJeu(Object cible) {
		this.getCapacite().executerEffetMiseEnJeu(cible);
	}

	public void executerEffetDisparition(Object cible) {
		this.getCapacite().executerEffetDisparition(cible);
	}
}
