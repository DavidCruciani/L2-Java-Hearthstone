package menu;

import plateau.Plateau;
import carte.ICarte;
import exception.HearthstoneException;
import menu.Console;

public class InterfaceJouerCarte extends Interface{

	public final static Console es = new Console();
	public InterfaceJouerCarte(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Jouer une carte";
	}

	@Override
	public boolean saisInteragir(String actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Plateau plateau) throws Exception {
		
		String cible;
		ICarte carte = null;
		while( carte == null)
		{
			System.out.println("Quelle carte jouer ? (un bout de son nom)");
			String choix = es.readLine();
			try {
				carte = plateau.getJoueurCourant().getCarteEnMain(choix);
			}
			catch(HearthstoneException e)
			{
				System.out.println(e.getMessage());
			}
		}
		try {
			
			plateau.getJoueurCourant().jouerCarte(carte);
		}
		catch (HearthstoneException e){
			//ALLER a l'interface cible
			System.out.println("C'est qui que tu vises ma poule ?");
			cible = es.readLine();
			plateau.getJoueurCourant().jouerCarte(carte, cible);
		}
		
	}

}
