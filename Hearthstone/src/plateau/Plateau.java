package plateau;

import java.util.ArrayList;

import carte.ICarte;
import exception.CapaciteException;
import exception.HearthstoneException;
import java.lang.Math;
import joueur.IJoueur;
/**
 * 
 * @author Alexis
 * Cette classe gère le plateau : joueur courant, adversaire, début et fin de partie
 *
 */
public class Plateau implements IPlateau{

	private ArrayList<IJoueur> joueurs = new ArrayList<IJoueur>();
	private boolean partie = false;
	private IJoueur courant;
	private static Plateau plateau = null;
	/**
	 * Création du plateau
	 */
	public Plateau() {
		this.courant = null;
		/*joueurs.add(null);
		joueurs.add(null);*/
	}
	
	public static Plateau plateau() {
		if(plateau==null)
			plateau=new Plateau();
		return plateau;
	}
	/**
	 * Ajoute un joueur à la partie
	 * @param joueur
	 * @throws HearthstoneException
	 * 
	 */
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if (joueur == null) throw new HearthstoneException("Joueur vide");
		if  (joueurs.size() == 2) throw new HearthstoneException("Dejà deux joueurs");
		joueurs.add(joueur);
	}
	/**
	 * Après vérification du nombre de joueur, démarrage de la partie
	 */
	public void demarrerPartie() throws HearthstoneException {
		if (joueurs.isEmpty()) throw new HearthstoneException("Il n'y a pas de joueur");
		if  (joueurs.get(1) == null) throw new HearthstoneException("1 seul joueur");
		courant =joueurs.get((int) Math.random() * 1);
		partie = true;
	}
	/**
	 * Vérifie l'état de la partie
	 * @return vrai (true) si la partie est démarrée, faux (false) sinon
	 */
	public boolean estDemaree() {
		return partie;
	}
/**
 * Termine le tour pour le joueur passé en paramètre
 * @param joueur
 * @throws HearthstoneException
 */
	public void finTour(IJoueur joueur) throws HearthstoneException {
		if (partie != true) throw new HearthstoneException("Partie non commencée");
		if (joueur != courant) throw new HearthstoneException("Joueur non courant");
		try {
			joueur.finirTour();
		} catch (HearthstoneException e) {
			System.out.println(e.getMessage());
		} catch (CapaciteException e) {
			System.out.println(e.getMessage());
		}
		this.getAdversaire(joueur).prendreTour();
	}
/**
 * Terminer la partie, le heros de l'adversaire meurt
 * @param joueur
 * @throws HearthstoneException
 */
	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
		System.out.println("Le joueur: "+joueur.getPseudo()+", gagne la partie !!!");
		// fin de la partie
		partie = false;
	}

/**
 * Retourne l'adversaire du joueur actuel (passé en paramètre)
 * @param joueur
 * @return Adversaire
 * @throws HearthstoneException
 */
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		if (joueur == null) throw new HearthstoneException("Joueur ne peut être vide");
		if (!joueurs.contains(joueur)) throw new HearthstoneException("Joueur inconnu");
		IJoueur autre;
		autre = joueurs.get(0);
		// il faut des joueurs aux pseudos différents
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
 * Met en place le joueur courant ou mise à jour
 * @param joueur
 * @throws HearthstoneException
 */
	public void setJoueurCourant(IJoueur joueur) throws HearthstoneException {
		if (partie == false) throw new HearthstoneException("Partie non commencée");
		if (joueur == null) throw new HearthstoneException("Joueur ne peut être vide");
		if (!joueurs.contains(joueur)) throw new HearthstoneException("Joueur inconnu");
		this.courant = joueur;
	}
	/**
	 * Etat du plateau
	 * @return les infos sur la partie, sur le joueur courant
	 */
	public String toString() {
		String partie = null;
		if (this.estDemaree()) {
			partie = "\nPartie démarrée\n"+ getJoueurCourant().toString() + " - " +getJoueurCourant().getHeros()+"\n\nVotre main :\n";
			partie += "######################################################\n";
			for(ICarte carte: getMainJoueurCourant())
			{
				partie += carte.toString();
			}
			partie += "######################################################\n";

			partie += "\nCartes en jeu :";
			partie += "\n=====================================================\n";
			for(ICarte carte: getJoueurCourant().getJeu())
			{
				partie += carte.toString();
			}
			partie += "\n\n======================\n\n";
			for(ICarte carte: getJeuAdversaire())
			{
				partie += carte.toString();
			}
			partie += "\n=====================================================\n\n";
			try {
				partie += getAdversaire(getJoueurCourant()).toString() + " - " +getAdversaire(getJoueurCourant()).getHeros() + "\n";
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
		} else
			partie = "Cette partie n'est pas demarrée";
		return partie;
	}
	
	public ArrayList<IJoueur> getJoueurs(){
		return this.joueurs;
	}
	
	public ArrayList<ICarte> getMainJoueurCourant(){
		return this.getJoueurCourant().getMain();
	}
	public ArrayList<ICarte> getJeuAdversaire(){
		try {
			return this.getAdversaire(getJoueurCourant()).getJeu();
		} catch (HearthstoneException e) {
			e.printStackTrace();
			return null;
		}
	}
}
