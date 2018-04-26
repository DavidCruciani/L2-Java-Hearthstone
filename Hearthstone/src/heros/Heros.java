package heros;

import capacite.ICapacite;

public class Heros {
	public static final int VIE_MAX=15;
	
	private String nom;
	private int vie;
	private ICapacite pouvoir;
	
	public Heros(String nom, ICapacite pouvoir) {
		this.setNom(nom);
		this.setPouvoir(pouvoir);
		this.vie=15;
	}
	
	public Heros(String nom) {
		this.setNom(nom);
		this.pouvoir=null;
		this.vie=15;
	}
	
	public String getNom() {
		return this.nom;
	}
	public int getVie() {
		return this.vie;
	}
	public ICapacite getPouvoir() {
		return this.pouvoir;
	}
	
	public void setNom(String nom) {
		if(nom == null)
			throw new IllegalArgumentException("Le nom doit pas etre nul");
		if(nom == "") {
			throw new IllegalArgumentException("Le nom doit pas etre vide");
		}
		this.nom=nom;
	}
	public void setPouvoir(ICapacite pouvoir) {
		this.pouvoir=pouvoir;
	}
	public void setVie(int vie) {
		this.vie=vie;
	}
	
	public void perdreVie(int degat) {
		this.setVie(this.getVie()-degat);
	}
	
	public boolean estMort() {
		return this.getVie()>=0;
	}
	
	public String toString() {
		if(pouvoir != null) {
			return "Nom[ "+this.getNom()+" ], Vie[ "+this.getVie()+" ], Pouvoir[ "+this.getPouvoir()+" ]";
		}
		return "Nom[ "+this.getNom()+" ], Vie[ "+this.getVie()+" ]";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Heros other = (Heros) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (vie != other.vie)
			return false;
		return true;
}
	
}
