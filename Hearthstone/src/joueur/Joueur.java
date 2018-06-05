package joueur;

import java.util.ArrayList;

import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;


/**
 * Joueur est une classe representant un joueur physique dans le jeu
 * @author David Cruciani, Alexis Nuss
 */

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
	public ArrayList<ICarte> getDeck(){
		return this.deck;
	}
	public Heros getHeros()
	{
		return this.hero;
	}
	
	public void setPseudo(String pseudo) {
		if(pseudo == null || pseudo=="")
			throw new IllegalArgumentException("Le pseudo ne peut pas etre nul ou vide");
		this.pseudo=pseudo;
	}
	public void setHero(Heros hero) {
		this.hero=hero;
	}

	public void finirTour() throws HearthstoneException, CapaciteException {
		for (ICarte carte : this.enJeu) {
			if(carte.getCapacite()==null)
				throw new HearthstoneException("Le serviteur n'a pas de Capacite");
			try {
				carte.executerEffetFinTour();
			}
			catch(CapaciteException e) {
				System.out.println(e.getMessage()+"\n");
			}
		}
	}
	
	public void setDeck(ArrayList<ICarte> deck) throws HearthstoneException {
		
		if(Plateau.plateau().estDemaree())
			throw new HearthstoneException("La partie est en cours ");
		if(deck==null)
			throw new HearthstoneException("Le deck a ajouter ne doit pas être null");
		this.deck=deck;

	}

	public ICarte getCarteEnJeu(String nomCarte) throws HearthstoneException {
			//System.out.println("= getCarteEnJeu ==============================================");
			//System.out.println("nomCarte : " + nomCarte + "this.enJeu : " + this.enJeu.size());
			for (ICarte carte : this.enJeu) {
				//System.out.println(carte.getNom() + carte.getNom().contains(nomCarte));
				if(carte.getNom().contains(nomCarte)) 
				{
				//	System.out.println("= return ==============================================");

					return carte;
				}
			}
			//System.out.println("= Exception ==============================================");

			throw new HearthstoneException("Carte pas en Jeu");
	}

	public ICarte getCarteEnMain(String nomCarte) throws HearthstoneException {
		for (ICarte carte : this.main) {
			if(carte.getNom().contains(nomCarte)) 
				return carte;
		}
		throw new HearthstoneException("Carte pas en Main");
	}

	public void jouerCarte(ICarte carte) throws HearthstoneException, CapaciteException {
		if(!main.contains(carte)) throw new HearthstoneException("Carte pas en Main");
		if(!(this.getMana() >= carte.getCout())) throw new HearthstoneException("Pas mana");
		if(carte instanceof Serviteur) {
			try {
				carte.executerEffetDebutMiseEnJeu(null);
			}
			catch ( IllegalArgumentException e) {
				this.mana = this.mana + carte.getCout();
				this.main.add(carte);
				this.getJeu().remove(carte);
				throw new HearthstoneException("Pas de cible");
			}
			catch(CapaciteException | HearthstoneException e) {
				System.out.println(e.getMessage());
			}
			enJeu.add(carte);
			main.remove(carte);
			this.mana -= carte.getCout();
			/*try {
				carte.executerEffetDebutMiseEnJeu(null);
			}
			catch (HearthstoneException e) {
				this.mana = this.mana + carte.getCout();
				this.main.add(carte);
				this.getJeu().remove(carte);
				throw new HearthstoneException("Pas de cible");
			}*/
		}
		else if(carte instanceof Sort){
			try {
				this.mana -= carte.getCout();
				main.remove(carte);
				
				carte.executerEffetDebutMiseEnJeu(null);
			
			}
			catch (HearthstoneException | IllegalArgumentException e) {
				this.mana = this.mana + carte.getCout();
				this.main.add(carte);
				throw new HearthstoneException("Pas de cible");
			}
			
			
		}
		
	}

	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException, CapaciteException {
		if(! main.contains(carte) )
			throw new HearthstoneException("Carte pas en Main");
		else {
			if(this.getMana() >= carte.getCout()) {
				if(carte instanceof Serviteur) {
					try {
						carte.executerEffetDebutMiseEnJeu(cible);
					}
					catch(CapaciteException | IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					enJeu.add(carte);
					main.remove(carte);
					this.mana -= carte.getCout();
				}
				else if(carte instanceof Sort){
					try {
						this.mana -= carte.getCout();
						main.remove(carte);
						carte.executerEffetDebutMiseEnJeu(cible);
					}
					catch (HearthstoneException | IllegalArgumentException e) {
						this.mana += carte.getCout();
						this.main.add(carte);
						throw new HearthstoneException("Pas de cible");
					}
					
				}
			}
			else throw new HearthstoneException("pas mana");
		}
	}

	public void perdreCarte(ICarte carte) throws HearthstoneException {
		if(enJeu.contains(carte)) {
			try {
				//System.out.println("tst2");
				((Serviteur) carte).executerEffetDisparition(null);
			} catch (CapaciteException e) {
				System.out.println(e.getMessage());
			}
			enJeu.remove(carte);
		}
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

	public void prendreTour() throws HearthstoneException {
		if(this.stockMana + 1 <= MAX_MANA) {
			this.stockMana ++;
		}
		this.mana = stockMana;
		try {
			piocher();
		}
		catch(HearthstoneException e) {
			System.out.println(e.getMessage());
		}
		Plateau.plateau().setJoueurCourant(this);
		getHeros().getPouvoir().setDejaUtilise(false);
		for(ICarte carte : Plateau.plateau().getJoueurCourant().getJeu()) {
			((Serviteur) carte).setAttendre(false);
			((Serviteur) carte).setPeutJouer(true);
		}
		
	}

	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException, CapaciteException {
		if(enJeu.contains(carte)) {
				carte.executerAction(cible);
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
