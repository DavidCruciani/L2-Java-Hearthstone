package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

public class AttaqueCible extends Capacite {
	private int degas;

	public AttaqueCible(String nom, String description, int degas) {
		super(nom, description);
		this.degas=degas;
	}
	
	public boolean getBesoinCible(){
		return true;
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
			if( ((Heros)cible).estMort()) {
				Plateau.plateau().gagnePartie(Plateau.plateau().getJoueurCourant());
			}
		}
		if(cible instanceof Serviteur) {
			if(aProvocation()) 
				throw new HearthstoneException("L'adversaire a une provocation");
			
			((Serviteur)cible).estAttaquer(degas);
			if( ((Serviteur)cible).disparait() ) {
				Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).perdreCarte( ((ICarte)cible) );
			}
		}
		setDejaUtilise(true);
			
	}
	
	public boolean aProvocation() throws HearthstoneException {
		for(ICarte carte : Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).getJeu() ) {
			if(((Serviteur) carte).getCapacite() instanceof Provocation)
				return true;
		}
		return false;
			
	}
	
	public void executerEffetDebutTour() throws CapaciteException {
		throw new CapaciteException("Pas d'effet de début de tour");
		
	}

	public void executerEffetDisparition(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'effet de disparition");
	
	}

	public void executerEffetFinTour() throws CapaciteException {
		throw new CapaciteException("Pas d'effet de fin de tour");
	
	}

	public void executerEffetMiseEnJeu(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'effet de mise en jeu");
	}

	public int getDegas() {
		return degas;
	}

}
