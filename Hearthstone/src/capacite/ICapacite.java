package capacite;

import exception.CapaciteException;
import exception.HearthstoneException;

public interface ICapacite {
		
		public abstract void executerAction(Object cible) throws HearthstoneException, CapaciteException;
	    public abstract void executerEffetDebutTour() throws CapaciteException;
	    public abstract void executerEffetDisparition(Object cible) throws HearthstoneException, CapaciteException;
	    public abstract void executerEffetFinTour() throws CapaciteException;
	    public abstract void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CapaciteException;
	    public abstract String getDescription();
	    public abstract String getNom();

}
