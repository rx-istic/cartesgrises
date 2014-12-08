package fr.istic.cg.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.istic.cg.donnees.CriteresSociete;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.persistance.Societe;

@Controller
public class SocieteController {

	@Autowired
	Creation cste;
	
	@Autowired
	Recherche recste;
	
	boolean firstRun = true;
	
	@Transactional
	void populate(){
		Societe aviva = new Societe();
    	aviva.setAdresse("impasse Bernard Madoff");
    	aviva.setRaisonSociale("Aviva SA");
    	aviva.setNumSiret("1324");
    	
    	cste.societe(aviva);
    	
    	Societe total = new Societe();
    	total.setAdresse("Luxembourg");
    	total.setRaisonSociale("TOTAL SA");
    	total.setNumSiret("852147");
    	
    	cste.societe(total);
    	

	}
	//TODO
	
	@RequestMapping(value = "/cherchersociete", method = RequestMethod.GET )
	public ModelAndView vehicule(	@RequestParam(value="adr", required=false) String adr, 
									@RequestParam(value="rs", required=false) String rs,
									@RequestParam(value="srt", required=false) String srt,
									ModelMap model) {
		if(firstRun){
			firstRun = false;
			//populate();	
		}

		CriteresSociete crtSte = new CriteresSociete();
		if(adr != null){
			crtSte.addCritere(CriteresSociete.ADRESSE_CLE, adr);
		}
		else if(rs != null){
			crtSte.addCritere(CriteresSociete.RAISON_SOCIALE_CLE, rs);
		}
		else if(srt != null){
			crtSte.addCritere(CriteresSociete.NUMSIRET_CLE, srt);
		}
		
		List<Societe> mySociete = recste.chercherSociete(crtSte);
		ModelAndView myModel = new ModelAndView("listeSocietes");
		
		myModel.addObject("societes", mySociete);
		return myModel;
	}
	/*
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
	}*/
}
