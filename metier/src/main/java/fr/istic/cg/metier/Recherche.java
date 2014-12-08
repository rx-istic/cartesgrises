package fr.istic.cg.metier;

import java.util.List;

import fr.istic.cg.donnees.CarteGriseDAO;
import fr.istic.cg.donnees.CriteresSociete;
import fr.istic.cg.donnees.CriteresVehicule;
import fr.istic.cg.donnees.ElementHistoriqueDAO;
import fr.istic.cg.donnees.ParticulierDAO;
import fr.istic.cg.donnees.SocieteDAO;
import fr.istic.cg.donnees.VehiculeDAO;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.Societe;
import fr.istic.cg.persistance.Vehicule;

public class Recherche {
	private CarteGriseDAO cgDAO= null;
	private VehiculeDAO vDAO= null;
	private SocieteDAO sDAO= null;
	private ParticulierDAO pDAO= null;
	private ElementHistoriqueDAO eDAO= null;
	
	public Recherche() {
		cgDAO = CarteGriseDAO.getInstance();
		vDAO = VehiculeDAO.getInstance();
		sDAO = SocieteDAO.getInstance();
		pDAO = ParticulierDAO.getInstance();
		eDAO = ElementHistoriqueDAO.getInstance();
	}

	public List<CarteGrise> chercherCG(){//CriteresCarteGrise criteres){
		//return cgDAO.search(criteres);
		return null;
	}
	
	/*
	public List<Proprietaire> chercherProprietaire(Criteres criteres){
		return new ArrayList<Proprietaire>();
	}*/
	
	public List<Societe> chercherSociete(CriteresSociete criteres){
		return sDAO.search(criteres);
	}
	
	public List<Vehicule> chercherVehicule(CriteresVehicule criteres){
		return vDAO.search(criteres);
	}
	
	

}
