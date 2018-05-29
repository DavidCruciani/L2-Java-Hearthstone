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
	
	public Serviteur ( Serviteur serviteur)
	{
		super(serviteur.getNom() , serviteur.getCout() , serviteur.getProprietaire());
		this.attaque=serviteur.getAttaque();
		this.pdv=serviteur.getPointDeVie();
		this.capacite=serviteur.getCapacite();
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
	public boolean getPeutJouer() {
		return peutJouer;
	}

	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
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
	
	public final Object clone (){
		return new Serviteur(this);
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
		String s="Serviteur [ " + super.toString() + ", Capacite " + this.getCapacite() + ", Attaque = " + this.getAttaque() + ", Vie = " + 
				this.getPointDeVie();
		if(this.getAttendre())
			s+=", (en attente)";
		if(this.getPeutJouer() && getProprietaire().getJeu().contains(this))
			s+= ", jouable";
		else if (!this.getPeutJouer() && getProprietaire().getJeu().contains(this))
			s+=" non jouable";
		s+="\n";
		return s;
	}	
	
	/**
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * La carte effectue l action que lui confere sa capacite
	 * si elle n est pas deja utilisee
	 * @throws HearthstoneException 
	 * @throws CapaciteException 
	 */
	public void executerAction(Object cible) throws HearthstoneException, CapaciteException{
		if(this.getAttendre())
			throw new HearthstoneException("Faut attendre un tour");
		if(this.getCapacite()==null)
			throw new CapaciteException("Le serviteur n'a pas de Capacite");
		
		if(this.getPeutJouer()) {
			if(cible == null)
				throw new IllegalArgumentException("Ta pas de cible");
			if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
				throw new IllegalArgumentException("Tu vise quoi la ? un oiseau ?");
			if(cible instanceof Heros) {
				if(aProvocation()) 
					throw new HearthstoneException("L'adversaire a une provocation");
		
				((Heros)cible).perdreVie(attaque);
				setPeutJouer(false);
				
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
				setPeutJouer(false);
				
				if( ((Serviteur)cible).disparait() ) 
					Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).perdreCarte( ((ICarte)cible) );
				if(this.disparait())
					//System.out.println("tst");
					try {
						Plateau.plateau().getJoueurCourant().perdreCarte((ICarte)this);
					}
				catch(HearthstoneException f) {
					System.out.println(f.getMessage());
				}
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
