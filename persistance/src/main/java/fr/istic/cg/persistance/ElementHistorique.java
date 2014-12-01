package fr.istic.cg.persistance;

import java.util.Date;

import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ElementHistorique {
	
	@Id
	@GeneratedValue
	private int id;
	
	
	@ManyToOne
	private Proprietaire refProrietaire;
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	
	public Proprietaire getRefProrietaire() {
		return refProrietaire;
	}
	public void setRefProrietaire(Proprietaire refProrietaire) {
		this.refProrietaire = refProrietaire;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
