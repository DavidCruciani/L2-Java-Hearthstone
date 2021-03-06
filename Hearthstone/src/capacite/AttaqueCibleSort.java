package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.CapaciteException;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

/**
 * AttaqueCibleSort est une classe issue de la classe Capacite
 * Cette capacite permet au serviteur d attaquer une cible adverse, le Hero ou un serviteur
 * meme si celui ci possede une provocation en jeu
 * @author David Cruciani, Alexis Nuss
 * @see Capacite
 */

public class AttaqueCibleSort extends Capacite {
	private int degats;

	public AttaqueCibleSort(String nom, String description, int degats) {
		super(nom, description);
		this.degats=degats;
	}
	public void executerAction(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'action");
	}
	
	public void executerEffetDebutTour() throws CapaciteException {
		throw new CapaciteException("Pas d'effet de d�but de tour");
		
	}

	public void executerEffetDisparition(Object cible) throws CapaciteException {
		throw new CapaciteException("Pas d'effet de disparition");
	
	}

	public void executerEffetFinTour() throws CapaciteException {
		throw new CapaciteException("Pas d'effet de fin de tour");
	
	}

	public void executerEffetMiseEnJeu(Object cible) throws CapaciteException {

		if(cible == null)
			throw new IllegalArgumentException("Ta pas de cible");
		if(getDejaUtilise())
			throw new IllegalArgumentException("Capacite deja utilise");
		if(!(cible instanceof Heros) && !(cible instanceof Serviteur))
			throw new IllegalArgumentException("Tu vise quoi la ? un oiseau ?");
		if(cible instanceof Heros) {
			((Heros)cible).perdreVie(degats);
			if( ((Heros)cible).estMort()) {
				Plateau.plateau().gagnePartie(Plateau.plateau().getJoueurCourant());
			}
		}
		if(cible instanceof Serviteur) {
			((Serviteur)cible).estAttaquer(degats);
			if( ((Serviteur)cible).disparait() ) {
				try {
					Plateau.plateau().getAdversaire(Plateau.plateau().getJoueurCourant()).perdreCarte( ((ICarte)cible) );
				}
				catch(HearthstoneException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		setDejaUtilise(true);
			
	}

}
