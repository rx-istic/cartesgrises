package fr.istic.cg.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.istic.cg.donnees.BaseDAO;
import fr.istic.cg.donnees.Criteres;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.ElementHistorique;
import fr.istic.cg.persistance.Particulier;
import fr.istic.cg.persistance.Societe;
import fr.istic.cg.persistance.Vehicule;

public class Recherche {
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
	

	public List<CarteGrise> chercherCG(Criteres<CarteGrise> criteres){//CriteresCarteGrise criteres){
		//return cgDAO.search(criteres);
		return null;
	}
	
	/*
	public List<Proprietaire> chercherProprietaire(Criteres criteres){
		return new ArrayList<Proprietaire>();
	}*/
	
	public List<Societe> chercherSociete(Criteres<Societe> criteres){
		return sDAO.search(criteres);
	}
	
	public List<Vehicule> chercherVehicule(Criteres<Vehicule> criteres){
		return vDAO.search(criteres);
	}
	
	

}
