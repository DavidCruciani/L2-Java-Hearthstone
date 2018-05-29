package menu;

import plateau.Plateau;

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
	public boolean saisInteragir(Object actionDemandee) {
		return getDescription().equals(actionDemandee);
	}

	public void executerInteraction(Object o) throws Exception {
		String choix = null ;
			System.out.println("Capituler ? (O ou o/N ou n)");
			choix = es.readLine();
			if(choix.contentEquals("o") || choix.contentEquals("O")) {
				Plateau.plateau().gagnePartie(Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()) );
				System.exit(0);
			}
			else if(choix.contentEquals("n") || choix.contentEquals("N"))
				return;
		
	}

}
