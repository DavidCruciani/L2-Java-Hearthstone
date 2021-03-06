package menu;

import capacite.Charge;
import carte.ICarte;
import carte.Sort;
import exception.CapaciteException;
import exception.HearthstoneException;
import plateau.Plateau;
/**
 * Permet d'executer un sort sur une carte en jeu du joueur courant
 * @author David Cruciani, Alexis Nuss
 *
 */
public class InterfaceChargeSort extends Interface {
	
	public final static Console es = new Console();
	public InterfaceChargeSort(Interface ihm) {
		super(ihm);
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public boolean saisInteragir(Object actionDemandee) {
		if(!(actionDemandee instanceof Sort))
			return false;
		if(!(((ICarte) actionDemandee).getCapacite() instanceof Charge))
			return false;
		return true;
	}

	@Override
	public void executerInteraction(Object o) throws Exception {
		Object cible = null;
			
			
				System.out.println("Quelle carte viser ? (un bout de son nom)");
				String choix = es.readLine();
				try {
					cible = Plateau.plateau().getJoueurCourant().getCarteEnJeu(choix);
				}
				catch(HearthstoneException e)
				{
					System.out.println(e.getMessage());
					return;
				}
			
				try {
					Plateau.plateau().getJoueurCourant().jouerCarte((ICarte) o, cible);
				} catch (HearthstoneException | CapaciteException e) {
					System.out.println(e.getMessage());
				}
		}
}
