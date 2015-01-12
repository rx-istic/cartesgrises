package fr.istic.cg.metier;

import org.springframework.beans.factory.annotation.Autowired;

import fr.istic.cg.donnees.BaseDAO;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.ElementHistorique;
import fr.istic.cg.persistance.Particulier;
import fr.istic.cg.persistance.Societe;
import fr.istic.cg.persistance.Vehicule;

/*Classe metier responsable des modifications*/
public class Modification {
	
	@Autowired
	BaseDAO<Vehicule> vDAO ;
	
	@Autowired
	BaseDAO<ElementHistorique> eDAO;
	
	@Autowired
	BaseDAO<Particulier> pDAO ;
	
	@Autowired
	BaseDAO<Societe> sDAO;
	
	@Autowired
	BaseDAO<CarteGrise> cDAO ;
	
	public void vehicule(Vehicule v){
		vDAO.update(v);
	}
	
	public void elementHistorique(ElementHistorique elh){
		eDAO.update(elh);
	}
	
	public void particulier(Particulier p){
		pDAO.update(p);
	}
	
	public void societe(Societe s){
		sDAO.update(s);
	}
	
	public void carteGrise(CarteGrise cg){
		cDAO.update(cg);
	}
}
