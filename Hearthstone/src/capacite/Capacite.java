package capacite;

/**
 * Capacite est une classe representant les actions que pourra faire la carte qui la possede
 * @author David Cruciani
 */
public abstract class Capacite implements ICapacite {
	private String nom;
	private String description;
	private boolean dejaUtilise = false;
	
	public Capacite(String nom, String description) {
		this.setNom(nom);
		this.setDescription(description);
	}
	
	public String getNom() {
		return this.nom;
	}
	public String getDescription() {
		return this.description;
	}
	public boolean getDejaUtilise() {
		return this.dejaUtilise;
	}
	
	public void setNom(String nom) {
		if(nom == null)
			throw new IllegalArgumentException("Le nom doit pas etre nul");
		if(nom == "") {
			throw new IllegalArgumentException("Le nom doit pas etre vide");
		}
		this.nom=nom;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setDejaUtilise(boolean dejaUtilise) {
		this.dejaUtilise = dejaUtilise;
	}
	
	public String toString() {
		return "[ Nom = "+this.getNom()+", Description = "+this.getDescription()+" ]";
	}
}
