package fr.istic.cg.metier;

import java.util.ArrayList;
import java.util.List;

import fr.istic.cg.donnees.CarteGriseDAO;
import fr.istic.cg.donnees.Criteres;
import fr.istic.cg.donnees.CriteresVehicule;
import fr.istic.cg.donnees.VehiculeDAO;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.Proprietaire;
import fr.istic.cg.persistance.Vehicule;

public class Recherche {
	private CarteGriseDAO cgDAO= null;
	private VehiculeDAO vDAO= null;
	public Recherche() {
		// TODO Auto-generated constructor stub
		cgDAO = CarteGriseDAO.getInstance();
		vDAO = VehiculeDAO.getInstance();
		//cgDAO.get(identifiant);
	}

	public List<CarteGrise> chercherCG(Criteres criteres){
		//TODO cgDAO.
		return null;
	}
	
	public List<Proprietaire> chercherProprietaire(Criteres criteres){
		return new ArrayList<Proprietaire>();
	}
	
	public List<Vehicule> chercherVehicule(CriteresVehicule criteres){
		return vDAO.search(criteres);
	}
	
	

}
