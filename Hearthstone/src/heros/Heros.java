package heros;

import capacite.ICapacite;

/**
 * Heros est une classe representant un hero du jeu
 * @author David Cruciani
 */


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
	/**
	 * constructeur dans le cas ou le hero a pas de capacite
	 * @param nom
	 * 			 nom du Heros choisie
	 */
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
	
	/**
	 * Met la vie du Hero à jour apres avoir subit une attaque 
	 * @param degat
	 * 			   vie enleve au Hero
	 */
	public void perdreVie(int degat) {
		this.setVie(this.getVie()-degat);
	}
	
	/**
	 * 
	 * @return true si la vie du Hero est inferieur ou egale à 0
	 */
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
