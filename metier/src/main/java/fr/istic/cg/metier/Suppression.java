package fr.istic.cg.metier;

import fr.istic.cg.donnees.CarteGriseDAO;
import fr.istic.cg.donnees.ElementHistoriqueDAO;
import fr.istic.cg.donnees.ParticulierDAO;
import fr.istic.cg.donnees.SocieteDAO;
import fr.istic.cg.donnees.VehiculeDAO;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.ElementHistorique;
import fr.istic.cg.persistance.Particulier;
import fr.istic.cg.persistance.Societe;
import fr.istic.cg.persistance.Vehicule;

public class Suppression {
	public void vehicule(Vehicule v){
		VehiculeDAO vDAO = VehiculeDAO.getInstance();
		vDAO.delete(v);
	}
	
	public void elementHistorique(ElementHistorique elh){
		ElementHistoriqueDAO elDAO = ElementHistoriqueDAO.getInstance();
		elDAO.delete(elh);
	}
	
	public void particulier(Particulier p){
		//ProprietaireDAO pDAO = ProprietaireDAO.getInstance();
		ParticulierDAO pDAO = ParticulierDAO.getInstance();
		pDAO.delete(p);
	}
	
	public void societe(Societe s){
		//ProprietaireDAO pDAO = ProprietaireDAO.getInstance();
		SocieteDAO sDAO = SocieteDAO.getInstance();
		sDAO.delete(s);
	}
	
	public void carteGrise(CarteGrise cg){
		CarteGriseDAO cgDAO = CarteGriseDAO.getInstance();
		cgDAO.delete(cg);
	}
}
