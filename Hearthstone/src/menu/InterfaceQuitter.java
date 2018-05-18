package menu;

import plateau.Plateau;
import menu.Console;

public class InterfaceQuitter extends Interface {
	public final static Console es = new Console();
	public InterfaceQuitter(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Quitter";
	}

	@Override
	public boolean saisInteragir(String actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	public void executerInteraction(Plateau plateau) throws Exception {
		es.println("Au revoir");
		System.exit(0);
	}

}
