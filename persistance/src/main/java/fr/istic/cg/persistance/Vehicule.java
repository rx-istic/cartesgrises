package fr.istic.cg.persistance;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Vehicule {
	//private int id;
	@Id
	private String numSerie;
	@Basic
	private String marque;
	@Basic
	private String modele;
	@Basic
	private String type;
	
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean hasNumSerie(){
		return this.numSerie != null && this.numSerie.length() > 0;
	}
	
	public boolean hasMarque(){
		return this.marque != null && this.marque.length() > 0;
	}
	
	public boolean hasModele(){
		return this.modele != null && this.modele.length() > 0;
	}
	
	public boolean hasType(){
		return this.type != null && this.type.length() > 0;
	}
}
