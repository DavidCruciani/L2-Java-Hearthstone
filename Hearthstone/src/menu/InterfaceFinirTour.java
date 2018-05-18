package menu;

import menu.Console;
import menu.Interface;
import plateau.Plateau;

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
	public boolean saisInteragir(String actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	public void executerInteraction(Plateau plateau) throws Exception {
		plateau.finTour(plateau.getJoueurCourant());
	}

}
