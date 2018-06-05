package joueur;

import java.util.ArrayList;

import carte.ICarte;
import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;

/**
 * Cette interface d�finit le comportement (normalement) minimal d'un joueur
 * @author David Cruciani, Alexis Nuss
 */
public interface IJoueur {
	
	/**
	 * Nombre max de mana que le joueur peut avoir (10 en principe)
	 */
	public static final int MAX_MANA = 10;
	
	/**
	 * Le nombre max de cartes qui peuvent composer un deck.
	 */
	public static final int TAILLE_DECK=15;

	/**
	 * Il se passe beaucoup de choses au moment o� le joueur finit le tour.
	 * @throws HearthstoneException 
	 * @throws CapaciteException
	 */
 	public abstract void finirTour() throws HearthstoneException, CapaciteException;
 	
 	/**
	 * Permet de rechercher une carte en jeu par rapport � un bout de son nom 
	 * (gr�ce � la fonction contains de la classe String).
	 * @param nom de la carte demandee
	 * @throws HearthstoneException 
	 * @return la carte demand�
	 */
    public abstract ICarte getCarteEnJeu(String nomCarte) throws HearthstoneException;
    
    /**
	 * Permet de rechercher une carte de la main par rapport � un bout de son nom 
	 * (gr�ce � la fonction contains de la classe String).
	 * @param nom de la carte demandee
	 * @throws HearthstoneException 
	 * @return la carte demand�
	 */
    public abstract ICarte getCarteEnMain(String nomCarte) throws HearthstoneException;
    
    /**
	 * getteur du heros
	 * @return heros du joueur
	 */
    public abstract Heros getHeros();
    
    /**
	 * c'est un getteur
	 * @return les cartes pos�es  sous forme d' ArrayList
	 */
    public abstract ArrayList<ICarte> getJeu();
    
    /**
	 * c'est un getteur
	 * @return main sous forme d' ArrayList
	 */
    public abstract ArrayList<ICarte> getMain();
    
    /**
	 * c'est un getteur
	 * @return le mana du joueur
	 */
    public abstract int getMana();
    
    /**
	 * c'est. un. getteur.
	 * @return le pseudo du joueur
	 */
    public abstract String getPseudo();
    
    /**
	 * devinez.
	 * @return le stock de mana du joueur
	 */
    public abstract int getStockMana();
    
    /**
	 * Pendant le tour, un joueur peut tenter de prendre une carte de sa main
	 *  et de la placer sur le board.
	 * @param carte 
	 * 			carte a jouer 
	 * @throws HearthstoneException 
	 * @throws CapaciteException 
	 */
    public abstract void jouerCarte(ICarte carte) throws HearthstoneException, CapaciteException;
    
    /**
	 * Pendant le tour, un joueur peut tenter de prendre une carte de sa main
	 *  et de la placer sur le board. 
	 * @param carte 
	 * 			carte a jouer
	 * @param cible 
	 * 			cible de la carte
	 * @throws HearthstoneException  
	 * @throws CapaciteException 
	 */
    public abstract void jouerCarte(ICarte carte, Object cible) throws HearthstoneException, CapaciteException;
    
    /**
	 * Lorsqu'une carte a fini sa vie (un serviteur qui meut, un sort qui vient d'�tre utilis�,
	 *  ...), il faut g�rer le jeu (une carte en moins...), 
	 *  ainsi que les effets li�s � la disparition de la carte (Rale d'agonie par exemple).
	 * @param carte 
	 * 			carte qui est perdue
	 * @throws HearthstoneException 
	 */
    public abstract void perdreCarte(ICarte carte) throws HearthstoneException;
    
    /**
	 * Au d�but de chaque tour, une carte du deck est choisie al�atoirement 
	 * et est plac�e dans la main du joueur.
	 * @throws HearthstoneException 
	 */
    public abstract void piocher() throws HearthstoneException;
    
    /**
	 * Il se passe beaucoup de choses au moment o� le joueur prend le tour.
	 * @throws HearthstoneException 
	 */
    public abstract void prendreTour() throws HearthstoneException;
    
    /**
	 * Lorsque qu'une carte est en jeu, le joueur peut utiliser cette carte.
	 * @param carte 
	 * 			carte a utiliser
	 * @param cible 
	 * 			cible de la carte utilis�e
	 * @throws HearthstoneException  
	 */
    public abstract void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException, CapaciteException;
   
    /**
	 * Cette fonction utilise le pouvoir du h�ros du joueur
	 *  pour faire quelque chose sur la cible.
	 * @param cible 
	 * 			cible du pouvoir du hero
	 * @throws HearthstoneException 
	 * @throws HearthstoneCapaciteException 
	 */
    public abstract void utiliserPouvoir(Object cible) throws HearthstoneException, CapaciteException;
}
