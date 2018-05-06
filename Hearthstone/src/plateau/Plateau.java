package plateau;

import java.util.ArrayList;
import exception.HearthstoneException;
import java.lang.Math;
import joueur.IJoueur;

public class Plateau implements IPlateau{

	private ArrayList<IJoueur> joueurs = new ArrayList<IJoueur>();
	private boolean partie = false;
	private IJoueur courant;
	private static Plateau plateau = null;
	
	public Plateau() {
		this.courant = null;
	}
	
	public static Plateau plateau() {
		if(plateau==null)
			plateau=new Plateau();
		return plateau;
	}
	
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if (joueur == null) throw new HearthstoneException("Joueur vide");
		// indexage dans arraylist ??
		if  (joueurs.get(1) != null) throw new HearthstoneException("Dejà deux joueurs");
		joueurs.add(joueur);
	}

	public void demarrerPartie() throws HearthstoneException {
		if (joueurs.isEmpty()) throw new HearthstoneException("Il n'y a pas de joueur");
		if  (joueurs.get(1) == null) throw new HearthstoneException("1 seul joueur");
		courant =joueurs.get((int) Math.random() * 1);
		partie = true;
	}

	public boolean estDemaree() {
		return partie;
	}

	public void finTour(IJoueur joueur) throws HearthstoneException {
		if (partie != true) throw new HearthstoneException("Partie non commencée");
		if (joueur != courant) throw new HearthstoneException("Joueur non courant");
		courant = getAdversaire(joueur);
	}

	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
		// le heros adverse meurt
		getAdversaire(joueur).getHeros().setVie(0);
		// fin de la partie
		partie = false;
	}


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


	public IJoueur getJoueurCourant() {
		if (partie == true)
			return courant;
		else
			return null;
	}

	public void setJoueurCourant(IJoueur joueur) throws HearthstoneException {
		if (partie == false) throw new HearthstoneException("Partie non commencée");
		if (joueur == null) throw new HearthstoneException("Joueur ne peut être vide");
		if (!joueurs.contains(joueur)) throw new HearthstoneException("Joueur inconnu");
		this.courant = joueur;
	}

}
