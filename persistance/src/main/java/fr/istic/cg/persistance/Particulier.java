package fr.istic.cg.persistance;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Particulier extends Proprietaire {
	
	@Basic
	private String nom;
	@Basic
	private String prenom;
	
	public Particulier(){
		super();
		typeProprietaire = TYPE_PARTICULIER;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public boolean hasNom(){
		return nom != null && nom.length() > 0;
	}
	
	public boolean hasPrenom(){
		return prenom != null && prenom.length() > 0;
	}

}
