package menu;

import menu.Console;
import menu.Interface;
import plateau.Plateau;
/**
 * Cette classe permet de finir le tour (en cours) du joueur (courant)
 * @author David Cruciani, Alexis Nuss
 *
 */
public class InterfaceFinirTour extends Interface {

	public final static Console es = new Console();

	public InterfaceFinirTour(Interface ihm) {
		super(ihm);
	}
	
	@Override
	public String getDescription() {
		return "Finir le tour";
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	public void executerInteraction(Object o) throws Exception {
		Plateau.plateau().finTour(Plateau.plateau().getJoueurCourant());
	}

}
