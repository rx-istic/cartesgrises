package fr.istic.cg.persistance;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="TYPE_P"//,
        //discriminatorType=DiscriminatorType.INTEGER,
        //columnDefinition="NUMBER(2)"
        )
public abstract class Proprietaire {

	@Id
	@GeneratedValue
	private int id;
	
	@Basic
	private String adresse;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public boolean hasId(){
		return id != 0;
	}
	
	public boolean hasAddresse(){
		return adresse != null && adresse.length() > 0;
	}
}
