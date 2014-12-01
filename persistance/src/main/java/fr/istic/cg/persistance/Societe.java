package fr.istic.cg.persistance;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Societe extends Proprietaire {

	@Basic
	private String raisonSociale;
	@Basic
	@Column(name = "PONEY_MAGIQUE", unique = true)
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
