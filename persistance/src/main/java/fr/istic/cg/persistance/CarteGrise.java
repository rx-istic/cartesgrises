package fr.istic.cg.persistance;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CarteGrise {

	@Id
	private String immatriculation;
	
	@OneToOne
	private Vehicule refVehicule;
	
	@OneToMany(fetch= FetchType.EAGER) 
	private Collection<ElementHistorique> historique;

	public CarteGrise(){
		historique  = new ArrayList<ElementHistorique>();
	}
	
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public Vehicule getRefVehicule() {
		return refVehicule;
	}
	public void setRefVehicule(Vehicule refVehicule) {
		this.refVehicule = refVehicule;
	}
	
	public Collection<ElementHistorique> getHistorique() {
		return historique;
	}
	
	public void addHistorique(ElementHistorique eh) {
		historique.add(eh);
	}
	
	public boolean hasImmatriculation(){
		return immatriculation != null && immatriculation.length() > 0;
	}
	public boolean hasVehicule(){
		return refVehicule != null;
	}
	public boolean hasHistorique(){
		return historique != null;
	}
}
