package plateau;

import joueur.IJoueur;

public interface IPlateau {
	
	public abstract void ajouterJoueur(IJoueur joueur);
    public abstract void demarrerPartie();
    public abstract boolean estDemaree();
    public abstract void finTour(IJoueur joueur);
    public abstract void gagnePartie(IJoueur joueur);
    public abstract IJoueur getAdversaire(IJoueur joueur);
    public abstract IJoueur getJoueurCourant();
	public abstract void setJoueurCourant(IJoueur joueur);
}
