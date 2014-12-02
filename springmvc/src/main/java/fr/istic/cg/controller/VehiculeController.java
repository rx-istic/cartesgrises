package fr.istic.cg.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.istic.cg.donnees.CriteresVehicule;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.persistance.Vehicule;

@Controller
public class VehiculeController {

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

	@RequestMapping(value = "/cherchervehicules", method = RequestMethod.GET )
	public ModelAndView vehicule(	@RequestParam(value="ns", required=false) String ns, 
									@RequestParam(value="mq", required=false) String mq,
									@RequestParam(value="md", required=false) String md,
									@RequestParam(value="tp", required=false) String tp,
									ModelMap model) {
		if(firstRun){
			firstRun = false;
			populate();	
		}

		CriteresVehicule crtVcl = new CriteresVehicule();
		if(ns != null){
			crtVcl.addCritere(CriteresVehicule.NUMSERIE_CLE, ns);
		}
		else if(mq != null){
			crtVcl.addCritere(CriteresVehicule.MARQUE_CLE, mq);
		}
		else if(md != null){
			crtVcl.addCritere(CriteresVehicule.MODELE_CLE, md);
		}
		else if(tp != null){
			crtVcl.addCritere(CriteresVehicule.TYPE_CLE, tp);
		}
		
		List<Vehicule> myVehicules = rec.chercherVehicule(crtVcl);
		ModelAndView myModel = new ModelAndView("listeVehicules");
		
		myModel.addObject("vehicules", myVehicules);
		return myModel;
	}
	
	 @RequestMapping(value = "/formulairevehicule", method = RequestMethod.GET )
	   public ModelAndView formulaireVehicule(ModelMap model) {
		 ModelAndView myModel = new ModelAndView("ajouterVehicule");//nom prochain JSP
		   model.addAttribute("vehiculemodel",new Vehicule());
	      return myModel;
	   }

	@RequestMapping(value = "/ajoutervehicule", method = RequestMethod.POST)
	public ModelAndView addVehicule(@ModelAttribute("vehiculemodel")Vehicule vehicule, 
			ModelMap model) {

		c.vehicule(vehicule);//on enregistre le véhicule
		
		ModelAndView myModel = new ModelAndView("redirect:/cherchervehicules");
		myModel.addObject("ns", vehicule.getNumSerie());
		return myModel;
	}
}
