package carte;

import capacite.ICapacite;
import capacite.Provocation;
import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;
import joueur.IJoueur;
import plateau.Plateau;

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
	 * @throws HearthstoneException 
	 */
	public void executerAction(Object cible) throws HearthstoneException{
		if(this.getAttendre())
			throw new HearthstoneException("Faut attendre un tour");
		
		if(this.peutJouer) {
			if(cible == null)
				throw new IllegalArgumentException("Ta pas de cible");
			if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
				throw new IllegalArgumentException("Tu vise quoi la ? un oiseau ?");
			if(cible instanceof Heros) {
				if(aProvocation()) 
					throw new HearthstoneException("L'adversaire a une provocation");
		
				((Heros)cible).perdreVie(attaque);
				peutJouer=false;
				
				if( ((Heros)cible).estMort()) {
					Plateau.plateau().gagnePartie(Plateau.plateau().getJoueurCourant());
				}
				
			}
			if(cible instanceof Serviteur) {
				if(aProvocation()) 
					if(!( ((Serviteur)cible).getCapacite() instanceof Provocation ) )
						throw new HearthstoneException("L'adversaire a une provocation");
				
				((Serviteur)cible).estAttaquer(attaque);
				this.estAttaquer(((Serviteur)cible).getAttaque());
				peutJouer=false;
				
				if( ((Serviteur)cible).disparait() ) 
					Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).perdreCarte( ((ICarte)cible) );
				if(this.disparait())
					Plateau.plateau().getJoueurCourant().perdreCarte(this);
			}
		}
		else throw new HearthstoneException("Tu la deja joué ce tour ci");
	}
		
	public boolean aProvocation() throws HearthstoneException {
		for(ICarte carte : Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu() ) {
			if(((Serviteur) carte).getCapacite() instanceof Provocation)
				return true;
		}
		return false;
				
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
