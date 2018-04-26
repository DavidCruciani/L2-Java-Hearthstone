package joueur;

import java.util.ArrayList;

import carte.ICarte;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

/* A finir pour le pouvoir et revoir quand plateau fait */

public class Joueur implements IJoueur {
	private String pseudo;
	private int mana = 0;
	private int stockMana = 0;
	private ArrayList<ICarte> deck = new ArrayList<ICarte>();
	private ArrayList<ICarte> main = new ArrayList<ICarte>();
	private ArrayList<ICarte> enJeu = new ArrayList<ICarte>();
	private Heros hero;
	
	public Joueur(String pseudo, Heros hero) {
		this.setPseudo(pseudo);
		this.setHero(hero);
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
	public int getMana() {
		return this.mana;
	}
	public int getStockMana() {
		return this.stockMana;
	}
	public ArrayList<ICarte> getMain(){
		return this.main;
	}
	public ArrayList<ICarte> getJeu(){
		return this.enJeu;
	}
	public Heros getHeros()
	{
		return this.hero;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo=pseudo;
	}
	public void setHero(Heros hero) {
		this.hero=hero;
	}

	public void finirTour() {
		for (ICarte carte : this.enJeu) {
			carte.executerEffetFinTour();
		}
		Plateau.finTour(this);
	}

	public ICarte getCarteEnJeu(String nomCarte) throws HearthstoneException {
			for (ICarte carte : this.enJeu) {
				if(carte.getNom().equals(nomCarte)) 
					return carte;
			}
			throw new HearthstoneException("Carte pas en Jeu");
	}

	public ICarte getCarteEnMain(String nomCarte) throws HearthstoneException {
		for (ICarte carte : this.main) {
			if(carte.getNom().equals(nomCarte)) 
				return carte;
		}
		throw new HearthstoneException("Carte pas en Main");
	}

	public void jouerCarte(ICarte carte) throws HearthstoneException {
		if(main.contains(carte) && this.getMana() - carte.getCout() > 0) {
			enJeu.add(carte);
			main.remove(carte);
			this.mana -= carte.getCout();
		}
		else
			throw new HearthstoneException("Carte pas en Main");
	}

	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
		if(main.contains(carte) && this.getMana() - carte.getCout() >= 0) {
			enJeu.add(carte);
			main.remove(carte);
			carte.executerEffetDebutMiseEnJeu(cible);
			this.mana -= carte.getCout();
		}
		else
			throw new HearthstoneException("Carte pas en Main");
	}

	public void perdreCarte(ICarte carte) throws HearthstoneException {
		if(enJeu.contains(carte)) 
			enJeu.remove(carte);
		else
			throw new HearthstoneException("Carte pas en Jeu");
	}

	public void piocher() throws HearthstoneException {
		if(deck.size() > 0) {
			int alea=(int)(Math.random() * deck.size());
			main.add( deck.get(alea) );
			deck.remove(alea);
		}
		else 
			throw new HearthstoneException("Deck vide tu peux plus piocher");
	}

	public void prendreTour() {
		if(this.mana + 1 < MAX_MANA) {
			this.mana ++;
			this.stockMana = mana;
		}
		try {
			piocher();
		}
		catch(HearthstoneException e) {
			e.printStackTrace();
		}
		
		Plateau.setJoueurCourant(this);
	}

	public void utiliserCarte(ICarte carte, Object cible) {
		if(enJeu.contains(carte)) {
			try {
				carte.executerAction(cible);
			}
			catch(HearthstoneException e) {
				e.printStackTrace();
			}
		}
	}

	public void utiliserPouvoir(Object cible) {

	}
	
	public String toString() {
		String s="Joueur: "+this.getPseudo()+", Mana: "+this.getMana();
		return s;
	}
}
