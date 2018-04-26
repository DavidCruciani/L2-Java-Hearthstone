package joueur;

import java.util.ArrayList;

import carte.ICarte;
import exception.HearthstoneException;
import heros.Heros;

public interface IJoueur {
	
	public static final int MAX_MANA = 10;
	public static final int TAILLE_DECK=15;

 	public abstract void finirTour();
    public abstract ICarte getCarteEnJeu(String nomCarte) throws HearthstoneException;
    public abstract ICarte getCarteEnMain(String nomCarte) throws HearthstoneException;
    public abstract Heros getHeros();
    public abstract ArrayList<ICarte> getJeu();
    public abstract ArrayList<ICarte> getMain();
    public abstract int getMana();
    public abstract String getPseudo();
    public abstract int getStockMana();
    public abstract void jouerCarte(ICarte carte) throws HearthstoneException;
    public abstract void jouerCarte(ICarte carte, Object cible) throws HearthstoneException;
    public abstract void perdreCarte(ICarte carte) throws HearthstoneException;
    public abstract void piocher() throws HearthstoneException;
    public abstract void prendreTour();
    public abstract void utiliserCarte(ICarte carte, Object cible);
    public abstract void utiliserPouvoir(Object cible) throws HearthstoneException;
}
