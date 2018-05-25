package menu;

import capacite.MarqueChasseur;
import carte.ICarte;
import carte.Sort;
import exception.HearthstoneException;
import plateau.Plateau;

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
		while (cible == null)
		{
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
		}
		Plateau.plateau().getJoueurCourant().jouerCarte((ICarte) o, cible);
	}

}
