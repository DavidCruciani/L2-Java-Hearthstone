package menu;

public class InterfaceQuitter extends Interface {
	public InterfaceQuitter(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Quitter";
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	public void executerInteraction(Object o) throws Exception {
		System.out.println("Au revoir");
		System.exit(0);
	}

}
