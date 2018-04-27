package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

public class AttaqueCible extends Capacite {
	private int degas;

	public AttaqueCible(String nom, String description, int degas) {
		super(nom, description);
		this.degas=degas;
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
			throw new IllegalArgumentException("Tu vise quoi la ? un oiseau ?");
		if(cible instanceof Heros) {
			if(aProvocation()) 
				throw new HearthstoneException("L'adversaire a une provocation");
	
			((Heros)cible).perdreVie(degas);
		}
		if(cible instanceof Serviteur) {
			if(aProvocation()) 
				throw new HearthstoneException("L'adversaire a une provocation");
			
			((Serviteur)cible).estAttaquer(degas);
		}
		setDejaUtilise(true);
			
	}
	
	public boolean aProvocation() {
		for(Serviteur carte : plateau.getAdversaire().getJeu() ) {
			if(carte.getCapacite() instanceof Provocation)
				return false;
		}
		return true;
			
	}
	
	public void executerEffetDebutTour() {
		
	}

	public void executerEffetDisparition(Object cible) {
	
	}

	public void executerEffetFinTour() {
	
	}

	public void executerEffetMiseEnJeu(Object cible) {
		
	}

}
