package menu;

public abstract class Interface {

	private	Interface	suivant = null;
	
	public Interface(Interface ihm) {
		suivant = ihm;
	}
	
	public Interface getSuivant() {
		return suivant;
	}

	public abstract String getDescription();
	public abstract boolean	saisInteragir(Object actionDemandee);
	public abstract void executerInteraction(Object o) throws Exception;	
	
	
	public void interagir(Object actionDemandees, Object o) throws Exception {
		if (saisInteragir(actionDemandees))
			executerInteraction(o);
		else if (suivant != null)
			suivant.interagir(actionDemandees,o);
		else
			throw new InteractionException("Il n'existe aucune interaction pour "+actionDemandees);
	}



}
