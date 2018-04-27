package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

public class AttaqueTotale extends Capacite {
	private int degas;
	
	public AttaqueTotale(String nom, String description, int degas) {
		super(nom, description);
		this.degas=degas;
	}
	
	public void executerAction(Object cible) throws HearthstoneException {
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
			throw new IllegalArgumentException("C'est pas un Heros que tu vise");
		if(cible instanceof Heros) {
				throw new HearthstoneException("Tu dois choisir un serviteur");
		}
		for(Serviteur carte : plateau.getAdversaire().getJeu() )
			((Serviteur)cible).estAttaquer(degas);
		
		setDejaUtilise(true);
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
