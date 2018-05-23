package joueur;

import java.util.ArrayList;

import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

/* revoir quand plateau fait */

/**
 * Joueur est une classe representant un joueur physique dans le jeu
 * @author David Cruciani
 */

public class Joueur implements IJoueur {
	private String pseudo;
	private int mana = 50;
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
	public ArrayList<ICarte> getDeck(){
		return this.deck;
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

	/**
	 * Le joueur a fait toute les actions qu il a souhaite et passe la main a l adversaire
	 * les capacites de fin de tour des cartes posees en jeu sont activees
	 * @throws HearthstoneException 
	 * @throws CapaciteException 
	 */
	public void finirTour() throws HearthstoneException, CapaciteException {
		for (ICarte carte : this.enJeu) {
			carte.executerEffetFinTour();
		}
	}
	
	public void setDeck(ArrayList<ICarte> deck) throws HearthstoneException {
		
		if(Plateau.plateau().estDemaree())
			throw new HearthstoneException("La partie est en cours ");
		if(deck==null)
			throw new HearthstoneException("Le deck a ajouter ne doit pas �tre null");
		this.deck=deck;

	}

	/**
	 * @param nomCarte
	 * 				  nom de la carte qu on veut recuperer
	 * @return la carte souhaite si elle existe bien sur le plateau
	 */
	public ICarte getCarteEnJeu(String nomCarte) throws HearthstoneException {
			for (ICarte carte : this.enJeu) {
				if(carte.getNom().contains(nomCarte)) 
					return carte;
			}
			throw new HearthstoneException("Carte pas en Jeu");
	}

	/**
	 * @param nomCarte
	 * 				  nom de la carte qu on veut recuperer
	 * @return la carte souhaite si elle existe bien dans la main du joueur
	 */
	public ICarte getCarteEnMain(String nomCarte) throws HearthstoneException {
		for (ICarte carte : this.main) {
			if(carte.getNom().contains(nomCarte)) 
				return carte;
		}
		throw new HearthstoneException("Carte pas en Main");
	}

	/**
	 * @param carte
	 * 				nom de la carte qu on veut jouer
	 * le joueur pose la carte de sa main sur le plateau 
	 * si elle existe bien dans sa main et si il a le mana pour le faire
	 * @throws CapaciteException 
	 */
	public void jouerCarte(ICarte carte) throws HearthstoneException, CapaciteException {
		if(!main.contains(carte)) throw new HearthstoneException("Carte pas en Main");
		if(!(this.getMana() >= carte.getCout())) throw new HearthstoneException("Pas mana");
		if(carte instanceof Serviteur) {
			enJeu.add(carte);
			main.remove(carte);
			this.mana -= carte.getCout();
			try {
				carte.executerEffetDebutMiseEnJeu(null);
			}
			catch (HearthstoneException e) {
				this.mana = this.mana + carte.getCout();
				this.main.add(carte);
				this.getJeu().remove(carte);
				throw new HearthstoneException("Pas de cible");
			}
		}
		else if(carte instanceof Sort){
			this.mana -= carte.getCout();
			main.remove(carte);
			try {
				carte.executerEffetDebutMiseEnJeu(null);
			}
			catch (HearthstoneException e) {
				this.mana = this.mana + carte.getCout();
				this.main.add(carte);
				throw new HearthstoneException("Pas de cible");
			}
		}
		
	}

	/**
	 * @param carte
	 * 				nom de la carte qu on veut jouer
	 * @param cible
	 * 				cible que l on veut atteindre par la capacite de mise en jeu
	 * le joueur pose la carte de sa main sur le plateau 
	 * si elle existe bien dans sa main et si il a le mana pour le faire
	 * et active sa capacite de mise en jeu sur la cible choisie
	 * @throws CapaciteException 
	 */
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException, CapaciteException {
		if( main.contains(carte) ){
			if(this.getMana() >= carte.getCout()) {
				if(carte instanceof Serviteur) {
					enJeu.add(carte);
					main.remove(carte);
					carte.executerEffetDebutMiseEnJeu(cible);
					this.mana -= carte.getCout();
				}
				else if(carte instanceof Sort){
					carte.executerEffetDebutMiseEnJeu(cible);
					this.mana -= carte.getCout();
					main.remove(carte);
				}
			}
			else throw new HearthstoneException("pas mana");
		}
		else
			throw new HearthstoneException("Carte pas en Main");
	}

	/**
	 * @param carte
	 * 				nom de la carte choisie
	 * retire du plateau la carte choisie si elle existe bien sur le plateau
	 */
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		if(enJeu.contains(carte)) 
			enJeu.remove(carte);
		else
			throw new HearthstoneException("Carte pas en Jeu");
	}

	/**
	 * Donnne une carte du deck pour la mettre dans la main du joueur
	 */
	public void piocher() throws HearthstoneException {
		if(deck.size() > 0) {
			int alea=(int)(Math.random() * deck.size());
			main.add( deck.get(alea) );
			deck.remove(alea);
		}
		else 
			throw new HearthstoneException("Deck vide tu peux plus piocher");
	}

	/**
	 * Le joueur commence a jouer son tour
	 * @throws HearthstoneException 
	 */
	public void prendreTour() throws HearthstoneException {
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
		Plateau.plateau().setJoueurCourant(this);
		getHeros().getPouvoir().setDejaUtilise(false);
		for(ICarte carte : Plateau.plateau().getJoueurCourant().getJeu()) {
			((Serviteur) carte).setAttendre(false);
			((Serviteur) carte).setPeutJouer(true);
		}
		
	}

	/**
	 * @param carte
	 * 				carte que le joueur souhaite utiliser
	 * @param cible
	 * 				cible que le joueur veut atteindre
	 * utilise la capacite de la carte si elle est bien sur le plateau
	 */
	public void utiliserCarte(ICarte carte, Object cible) {
		if(enJeu.contains(carte)) {
			try {
				carte.executerAction(cible);
			}
			catch(HearthstoneException | CapaciteException e) {
				e.printStackTrace();
			}
		}
	}


	public void utiliserPouvoir(Object cible) throws HearthstoneException, CapaciteException{
		if(hero.getPouvoir() != null) {
			hero.getPouvoir().executerEffetMiseEnJeu(cible);
		}
		else
			throw new HearthstoneException("Ton Hero est mauvais, il a pas de pouvoir");
	}
	
	public String toString() {
		String s="Joueur: "+this.getPseudo()+", Mana: "+this.getMana();
		return s;
	}
}
