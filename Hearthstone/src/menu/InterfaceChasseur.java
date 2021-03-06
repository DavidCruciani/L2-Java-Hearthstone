package menu;

import capacite.MarqueChasseur;
import carte.ICarte;
import carte.Sort;
import exception.CapaciteException;
import exception.HearthstoneException;
import plateau.Plateau;
/**
 * Cette classe permet d'executer la capacit� MarqueChasseur sur une carte en jeu de l'aversaire
 * Demande une cible
 * @author David Cruciani, Alexis Nuss
 * @see MarqueChasseur
 *
 */
public class InterfaceChasseur extends Interface {
	public final static Console es = new Console();
	public InterfaceChasseur(Interface ihm) {
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
		if(!(((ICarte) actionDemandee).getCapacite() instanceof MarqueChasseur))
			return false;
		return true;
	}

	public void executerInteraction(Object o) throws Exception {
		
		Object cible = null;
		
		//	System.out.println("jeu joueur1 : " + ((Joueur) joueur1).getJeu().size());

			System.out.println("Quelle carte viser ? (un bout de son nom)-- interface chasseur");
			String choix = es.readLine();
			try {
				cible = Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getCarteEnJeu(choix);
				//System.out.println(cible.toString());
			}
			catch(HearthstoneException e)
			{
				System.out.println(e.getMessage());
			}
		
			try {
				Plateau.plateau().getJoueurCourant().jouerCarte((ICarte) o, cible);
			} catch (HearthstoneException | CapaciteException e) {
				System.out.println(e.getMessage());
			}
	}

}
