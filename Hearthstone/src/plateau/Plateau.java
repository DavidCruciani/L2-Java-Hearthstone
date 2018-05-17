package plateau;

import java.util.ArrayList;
import exception.HearthstoneException;
import java.lang.Math;
import joueur.IJoueur;
/**
 * 
 * @author Alexis
 * Cette classe g�re le plateau : joueur courant, adversaire, d�but et fin de partie
 *
 */
public class Plateau implements IPlateau{

	private ArrayList<IJoueur> joueurs = new ArrayList<IJoueur>();
	private boolean partie = false;
	private IJoueur courant;
	private static Plateau plateau = null;
	/**
	 * Cr�ation du plateau
	 */
	public Plateau() {
		this.courant = null;
	}
	
	public static Plateau plateau() {
		if(plateau==null)
			plateau=new Plateau();
		return plateau;
	}
	/**
	 * Ajoute un joueur � la partie
	 * @param joueur
	 * @throws HearthstoneException
	 * 
	 */
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if (joueur == null) throw new HearthstoneException("Joueur vide");
		// indexage dans arraylist ??
		if  (joueurs.get(1) != null) throw new HearthstoneException("Dej� deux joueurs");
		joueurs.add(joueur);
	}
	/**
	 * Apr�s v�rification du nombre de joueur, d�marrage de la partie
	 */
	public void demarrerPartie() throws HearthstoneException {
		if (joueurs.isEmpty()) throw new HearthstoneException("Il n'y a pas de joueur");
		if  (joueurs.get(1) == null) throw new HearthstoneException("1 seul joueur");
		courant =joueurs.get((int) Math.random() * 1);
		partie = true;
	}
	/**
	 * V�rifie l'�tat de la partie
	 * @return vrai (true) si la partie est d�marr�e, faux (false) sinon
	 */
	public boolean estDemaree() {
		return partie;
	}
/**
 * Termine le tour pour le joueur pass� en param�tre
 * @param joueur
 * @throws HearthstoneException
 */
	public void finTour(IJoueur joueur) throws HearthstoneException {
		if (partie != true) throw new HearthstoneException("Partie non commenc�e");
		if (joueur != courant) throw new HearthstoneException("Joueur non courant");
		courant = getAdversaire(joueur);
	}
/**
 * Terminer la partie, le heros de l'adversaire meurt
 * @param joueur
 * @throws HearthstoneException
 */
	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
		// le heros adverse meurt
		getAdversaire(joueur).getHeros().setVie(0);
		// fin de la partie
		partie = false;
	}

/**
 * Retourne l'adversaire du joueur actuel (pass� en param�tre)
 * @param joueur
 * @return Adversaire
 * @throws HearthstoneException
 */
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		if (joueur == null) throw new HearthstoneException("Joueur ne peut �tre vide");
		if (!joueurs.contains(joueur)) throw new HearthstoneException("Joueur inconnu");
		IJoueur autre;
		autre = joueurs.get(0);
		// il faut des joueurs aux pseudos diff�rents
		if (autre.getPseudo().equals(joueur.getPseudo()))
			return joueurs.get(1);
		else
			return joueurs.get(0);
	}

/**
 * Joueur courant
 * @return le joueur courant
 */
	public IJoueur getJoueurCourant() {
		if (partie == true)
			return courant;
		else
			return null;
	}
/**
 * Met en place le joueur courant ou mise � jour
 * @param joueur
 * @throws HearthstoneException
 */
	public void setJoueurCourant(IJoueur joueur) throws HearthstoneException {
		if (partie == false) throw new HearthstoneException("Partie non commenc�e");
		if (joueur == null) throw new HearthstoneException("Joueur ne peut �tre vide");
		if (!joueurs.contains(joueur)) throw new HearthstoneException("Joueur inconnu");
		this.courant = joueur;
	}
	/**
	 * Etat du plateau
	 * @return les infos sur la partie, sur le joueur courant
	 */
	public String toString() {
		String partie = null;
		if (this.estDemaree())
			try {
				partie = "Cette partie est d�mar�e" + this.getJoueurCourant() + " affronte" + this.getAdversaire(this.getJoueurCourant()) + "; C'est � vous : "+this.getJoueurCourant().toString();
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
		else
			partie = "Cette partie n'est pas demarr�e";
		return partie;
	}

}
