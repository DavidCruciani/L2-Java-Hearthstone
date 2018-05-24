package carte;

import capacite.ICapacite;
import exception.CapaciteException;
import exception.HearthstoneException;
import joueur.IJoueur;

public interface ICarte {
	public abstract boolean disparait();
    public abstract void executerAction(Object cible) throws HearthstoneException, CapaciteException;
    public abstract void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CapaciteException;
    public abstract void executerEffetDebutTour(Object cible) throws CapaciteException;
    public abstract void executerEffetFinTour() throws CapaciteException;
    public abstract int getCout();
    public abstract String getNom();
    public abstract IJoueur getProprietaire();
	public abstract ICapacite getCapacite();
}
