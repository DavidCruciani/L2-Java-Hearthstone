package carte;

import capacite.ICapacite;
import exception.CapaciteException;
import exception.HearthstoneException;
import joueur.IJoueur;

/**
 * Une carte, ben c'est une carte du jeu quoi ! Une carte peut �tre une carte de sort 
 * ou une carte serviteur (on ne s'occupe pas des autres sortes de cartes).
 * @author David Cruciani, Alexis Nuss
*/
public interface ICarte {
	/**
	 * Fonction qui teste si les conditions pour que la carte soit encore pr�sente
	 * au tour suivant.
	 * @return true si la vie de la carte est inferieur ou egale a 0
	 */
	public abstract boolean disparait();
	
	
	/**
	 * Une carte peut avoir une action qui se commande � n'importe quel moment du tour 
	 * lorsqu'elle est en jeu
	 * @param cible
	 * @throws HearthstoneException
	 * @throws CapaciteException 
	 */
    public abstract void executerAction(Object cible) throws HearthstoneException, CapaciteException;
    
    
    /**
	 * Une carte peut avoir un effet au d�but de sa mise en jeu
	 * @param cible
	 * @throws HearthstoneException 
	 * @throws CapaciteException 
	 */
    public abstract void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CapaciteException;
    
    
    /**
	 * Une carte peut avoir un effet au d�but de chaque tour o� elle est en jeu
	 * @throws CapaciteException
	 */
    public abstract void executerEffetDebutTour(Object cible) throws CapaciteException;
    
    
    
    /**
	 * Une carte peut avoir un effet au moment de sa disparition du jeu
	 * @param cible
	 * @throws HearthstoneException
     * @throws CapaciteException 
	 */
    public abstract void executerEffetDisparition(Object cible) throws HearthstoneException, CapaciteException ;
    
    
    /**
	 * Une carte peut avoir un effet � la fin d'un chaque tour o� elle est en jeu
	 * @throws HearthstoneException
	 */
    public abstract void executerEffetFinTour() throws CapaciteException;
    
    
    public abstract int getCout();
 
    public abstract String getNom();
  
    public abstract IJoueur getProprietaire();
    
	public abstract ICapacite getCapacite();
}
