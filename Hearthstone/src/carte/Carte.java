package carte;

import joueur.IJoueur;

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
		this.nom=nom;;
	}
	public void setCout(int cout) {
		this.cout=cout;
	}
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public String toString() {
		return "Nom[ "+this.getNom()+" ], Cout[ "+this.getCout()+" ]";
	}
	
	/*public boolean equals(Object o) {
		if(this==o) return true;
		if (o==null) return false;
		if(!(o instanceof Carte)) return false;
		Carte a=(Carte)o;
		return (a.getNom().equals(this.getNom()) && a.getCout().equals(this.getCout()) );
	}*/
}
