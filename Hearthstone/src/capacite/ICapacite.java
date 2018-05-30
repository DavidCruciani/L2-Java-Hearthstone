package capacite;

import exception.CapaciteException;
import exception.HearthstoneException;


/**
* Une capacit�, c'est l'abstraction g�n�rique pour d�crire, le pouvoir du h�ros,
* le sort d'une carte Sort, la capacit� d'un serviteur, etc.
* @author David Cruciani, Alexis Nuss
*/
public interface ICapacite {
		
	/**
	* Certaines capacit� agissent quand on le demande, et �ventuellement sur une cible...
	* @param cible
	* @throws HearthstoneException
	* @throws CapaciteException
	*/
	public abstract void executerAction(Object cible) throws HearthstoneException, CapaciteException;
	
	
	/**
	  * Certaines capacit� agissent en d�but de tour 
	 * @throws CapaciteException
	 */
    public abstract void executerEffetDebutTour() throws CapaciteException;
    
    
    /**
	* Certaines capacit� agissent lorsque la carte dispara�t du jeu
	 * @param cible
	 * @throws HearthstoneException
	 * @throws CapaciteException
	 */
    public abstract void executerEffetDisparition(Object cible) throws HearthstoneException, CapaciteException;
    
    
    /**
	 * Certaines capacit� agissent en fin de tour
	 * @throws CapaciteException
	 */
    public abstract void executerEffetFinTour() throws CapaciteException;
    
    
    /**
	 * Certaines capacit� agissent en d�but de mise en jeu
	 * @throws HearthstoneException
	 * @throws CapaciteException 
	 */
    public abstract void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CapaciteException;
    
    public abstract String getDescription();
    
    public abstract String getNom();
    
	public abstract void setDejaUtilise(boolean dejaUtilise);

}
