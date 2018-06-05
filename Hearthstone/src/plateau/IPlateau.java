package plateau;

import exception.HearthstoneException;
import joueur.IJoueur;
/**
 * Cette interface gère le plateau : joueur courant, adversaire, début et fin de partie
 * @author David Cruciani, Alexis Nuss
 *
 */
public interface IPlateau {
	/**
	 * Ajoute un joueur à la partie
	 * @param joueur
	 * @throws HearthstoneException
	 * 
	 */
	public abstract void ajouterJoueur(IJoueur joueur) throws HearthstoneException;
	/**
	 * Après vérification du nombre de joueur, démarrage de la partie
	 */
    public abstract void demarrerPartie() throws HearthstoneException;
    /**
     * La partie est elle démarée ?
     * @return true si la partie est démarrée
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
     * Retourne l'adversaire du joueur actuel (passé en paramètre)
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
     * Met en place le joueur courant ou mise à jour
     * @param joueur
     * @throws HearthstoneException
     */
	public abstract void setJoueurCourant(IJoueur joueur) throws HearthstoneException;
}
