package fr.istic.cg.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import business.Student;
import fr.istic.cg.donnees.CriteresVehicule;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.persistance.Vehicule;

@Controller
public class CarteGriseController {

	@Autowired
	Creation c;
	
	@Autowired
	Recherche rec;
	
	boolean firstRun = true;
	
	@Transactional
	void populate(){
		Vehicule v = new Vehicule();
    	v.setNumSerie("ABC");
    	v.setMarque("XX");
		
		c.vehicule(v);
		
		
		
    	
    	Vehicule v3 = new Vehicule();
    	v3.setNumSerie("ABCD");
    	v3.setMarque("XX");
    	
    	Vehicule v4 = new Vehicule();
    	v4.setNumSerie("ABCDE");
    	v4.setMarque("YY");
    	
    	c.vehicule(v3);
    	c.vehicule(v4);
    	

	}

	@RequestMapping(value = "/cartesgrises", method = RequestMethod.GET )
	public ModelAndView cartegrise(ModelMap model) {
		if(firstRun){
			firstRun = false;
			populate();	
		}
		
		List<Vehicule> myVehicules = rec.chercherVehicule(new CriteresVehicule());
		ModelAndView myModel = new ModelAndView("listeCartesGrises");
		
		myModel.addObject("vehicules", myVehicules);
		return myModel;
	}

	@RequestMapping(value = "/searchcartegrises", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("cartegrisemodel")Student student, 
			ModelMap model) {
		//TODO add here the communication with the business part

		return "Not Implemented Yet";
	}
}
