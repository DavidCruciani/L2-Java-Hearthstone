package plateau;
import java.util.ArrayList;
import java.lang.Math;

import joueur.IJoueur;
import joueur.Joueur;

public class Plateau implements IPlateau{

	private ArrayList<IJoueur> joueurs = new ArrayList<IJoueur>();
	private boolean partie = false;
	private IJoueur courant;
	
	public void ajouterJoueur(IJoueur joueur) throws jeu.HearthstoneException {
		if (joueur == null) throw new jeu.HearthstoneException("Joueur vide");
		// indexage dans arraylist ??
		if  (joueurs.get(1) != null) throw new jeu.HearthstoneException("Dejà deux joueurs");
		joueurs.add(joueur);
	}

	public void demarrerPartie() throws jeu.HearthstoneException {
		if (joueurs.isEmpty()) throw new jeu.HearthstoneException("Il n'y a pas de joueur");
		if  (joueurs.get(1) == null) throw new jeu.HearthstoneException("1 seul joueur");
		courant =Math.random();
		partie = true;
	}

	public boolean estDemaree() {
		return partie;
	}

	public void finTour(IJoueur joueur) throws jeu.HearthstoneException {
		if (partie != true) throw new jeu.HearthstoneException("Partie non commencée");
		if (joueur != courant) throw new jeu.HearthstoneException("Joueur non courant");
		courant = getAdversaire(joueur);
	}

	public void gagnePartie(IJoueur joueur) {
		// le heros adverse meurt
		getAdversaire(joueur).getHeros().setVie(0);
		// fin de la partie
		partie = false;
	}


	public IJoueur getAdversaire(IJoueur joueur) {
		if (joueur == null) throw new jeu.HearthstoneException("Joueur ne peut être vide");
		if (!joueurs.contains(joueur)) throw new jeu.HearthstoneException("Joueur inconnu");
		IJoueur autre;
		autre = joueurs.get(0);
		// il faut des joueurs aux pseudos différents
		if (autre.getPseudo()equals(joueur.getPseudo()))
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

	public void setJoueurCourant(IJoueur joueur) throws jeu.HearthstoneException {
		if (partie == false) throw new jeu.HearthstoneException("Partie non commencée");
		if (joueur == null) throw new jeu.HearthstoneException("Joueur ne peut être vide");
		if (!joueurs.contains(joueur)) throw new jeu.HearthstoneException("Joueur inconnu");
		this.courant = joueur;
	}

}
