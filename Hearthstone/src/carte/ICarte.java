package carte;

import exception.HearthstoneException;
import joueur.IJoueur;

public interface ICarte {
	public abstract boolean disparait();
    public abstract void executerAction(Object cible) throws HearthstoneException;
    public abstract void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException;
    public abstract void executerEffetDebutTour(Object cible);
    public abstract void executerEffetFinTour();
    public abstract int getCout();
    public abstract String getNom();
    public abstract IJoueur getProprietaire();
}
