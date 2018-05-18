package menu;

import plateau.Plateau;

public abstract class Interface {

	private	Interface	suivant = null;
	
	public Interface(Interface ihm) {
		suivant = ihm;
	}
	
	public Interface getSuivant() {
		return suivant;
	}

	public abstract String getDescription();
	public abstract boolean	saisInteragir(String actionDemandee);
	public abstract void	executerInteraction(Plateau plateau) throws Exception;	
	
	
	public void interagir(String actionDemandees, Plateau plateau) throws Exception {
		if (saisInteragir(actionDemandees))
			executerInteraction(plateau);
		else if (suivant != null)
			suivant.interagir(actionDemandees,plateau);
		else
			throw new InteractionException("Il n'existe aucune interaction pour "+actionDemandees);
	}



}
