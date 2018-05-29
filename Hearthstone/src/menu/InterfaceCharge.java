package menu;

import capacite.Charge;
import carte.ICarte;
import carte.Serviteur;
import plateau.Plateau;

public class InterfaceCharge extends Interface {
	//public final static Console es = new Console();
	public InterfaceCharge(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		if(!(actionDemandee instanceof Serviteur))
			return false;
		if(!(((ICarte) actionDemandee).getCapacite() instanceof Charge))
			return false;
		return true;
	}

	@Override
	public void executerInteraction(Object o) throws Exception {
		Plateau.plateau().getJoueurCourant().jouerCarte((ICarte) o, (ICarte) o);
	}

}
