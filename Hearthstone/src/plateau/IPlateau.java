package plateau;

import exception.HearthstoneException;
import joueur.IJoueur;
/**
 * Cette interface g�re le plateau : joueur courant, adversaire, d�but et fin de partie
 * @author David Cruciani, Alexis Nuss
 *
 */
public interface IPlateau {
	/**
	 * Ajoute un joueur � la partie
	 * @param joueur
	 * @throws HearthstoneException
	 * 
	 */
	public abstract void ajouterJoueur(IJoueur joueur) throws HearthstoneException;
	/**
	 * Apr�s v�rification du nombre de joueur, d�marrage de la partie
	 */
    public abstract void demarrerPartie() throws HearthstoneException;
    /**
     * La partie est elle d�mar�e ?
     * @return true si la partie est d�marr�e
     */
    public abstract boolean estDemaree();
    /**
     * Terminer la partie, le heros de l'adversaire meurt
     * @param joueur
     * @throws HearthstoneException
     */
    public abstract void finTour(IJoueur joueur) throws HearthstoneException;
    public abstract void gagnePartie(IJoueur joueur);
    /**
     * Retourne l'adversaire du joueur actuel (pass� en param�tre)
     * @param joueur
     * @return Adversaire
     * @throws HearthstoneException
     */
    public abstract IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException;
    /**
     * Joueur courant
     * @return le joueur courant
     */
    public abstract IJoueur getJoueurCourant() ;
    /**
     * Met en place le joueur courant ou mise � jour
     * @param joueur
     * @throws HearthstoneException
     */
	public abstract void setJoueurCourant(IJoueur joueur) throws HearthstoneException;
}
