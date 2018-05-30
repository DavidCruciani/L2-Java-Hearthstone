package carte;

import joueur.IJoueur;

/**
 * Carte est une classe representant une carte dans le jeu
 * @author David Cruciani, Alexis Nuss
 *
 */

public abstract class Carte implements ICarte {
	private String nom;
	private int cout;
	private IJoueur proprietaire;
	
	public Carte(String nom, int cout, IJoueur proprietaire) {
		this.setNom(nom);
		this.setCout(cout);
		this.setProprietaire(proprietaire);
	}
	
	public String getNom() {
		return this.nom;
	}
	public int getCout() {
		return this.cout;
	}
	public IJoueur getProprietaire() {
		return this.proprietaire;
	}
	
	public void setNom(String nom) {
		if(nom == null)
			throw new IllegalArgumentException("Le nom doit pas etre nul");
		if(nom == "") {
			throw new IllegalArgumentException("Le nom doit pas etre vide");
		}
		this.nom=nom;
	}
	public void setCout(int cout) {
		if(cout<0)
			throw new IllegalArgumentException("Le cout doit etre positif");
		this.cout=cout;
	}
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public String toString() {
		return "Nom = "+this.getNom()+", Cout = "+this.getCout();
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carte other = (Carte) obj;
		if (cout != other.cout)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	} 
}
