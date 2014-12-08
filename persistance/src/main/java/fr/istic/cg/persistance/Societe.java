package fr.istic.cg.persistance;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Societe extends Proprietaire {

	@Basic
	private String raisonSociale;
	
	@Basic
	private String numSiret;
	
	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public String getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}


}
