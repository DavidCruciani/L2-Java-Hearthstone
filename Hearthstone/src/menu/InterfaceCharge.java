package menu;

import capacite.Charge;
import carte.ICarte;
import carte.Serviteur;
import plateau.Plateau;
/**
 * Permet d'executer la capacité Charge sur une carte passée en paramètre 
 * @author David Cruciani, Alexis Nuss
 * @see Charge 
 *
 */
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
