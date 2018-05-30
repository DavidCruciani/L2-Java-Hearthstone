package capacite;

import exception.CapaciteException;
import exception.HearthstoneException;


/**
* Une capacité, c'est l'abstraction générique pour décrire, le pouvoir du héros,
* le sort d'une carte Sort, la capacité d'un serviteur, etc.
* @author David Cruciani, Alexis Nuss
*/
public interface ICapacite {
		
	/**
	* Certaines capacité agissent quand on le demande, et éventuellement sur une cible...
	* @param cible
	* @throws HearthstoneException
	* @throws CapaciteException
	*/
	public abstract void executerAction(Object cible) throws HearthstoneException, CapaciteException;
	
	
	/**
	  * Certaines capacité agissent en début de tour 
	 * @throws CapaciteException
	 */
    public abstract void executerEffetDebutTour() throws CapaciteException;
    
    
    /**
	* Certaines capacité agissent lorsque la carte disparaît du jeu
	 * @param cible
	 * @throws HearthstoneException
	 * @throws CapaciteException
	 */
    public abstract void executerEffetDisparition(Object cible) throws HearthstoneException, CapaciteException;
    
    
    /**
	 * Certaines capacité agissent en fin de tour
	 * @throws CapaciteException
	 */
    public abstract void executerEffetFinTour() throws CapaciteException;
    
    
    /**
	 * Certaines capacité agissent en début de mise en jeu
	 * @throws HearthstoneException
	 * @throws CapaciteException 
	 */
    public abstract void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CapaciteException;
    
    public abstract String getDescription();
    
    public abstract String getNom();
    
	public abstract void setDejaUtilise(boolean dejaUtilise);

}
