package menu;

import plateau.Plateau;

public class InterfaceCible extends Interface {

	public final static Console es = new Console();
	public InterfaceCible(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return "Determine une cible";
	}

	@Override
	public boolean saisInteragir(String actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	@Override
	public void executerInteraction(Plateau plateau) throws Exception {
		int cible = 0;
		while (cible != 1 && cible != 2)
		{
			System.out.println("Quelle est votre cible ?\n");
			System.out.println("1. Le héros\n");
			System.out.println("2. Une autre carte\n");
			cible = es.readInt();
		}
		if (cible == 1)
		{
			
		}
		else {
			
		}
	}
}