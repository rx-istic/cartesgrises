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

import fr.istic.cg.donnees.CriteresSociete;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Modification;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.metier.Suppression;
import fr.istic.cg.persistance.Societe;

@Controller
public class SocieteController {

	@Autowired
	Creation c;
	
	@Autowired
	Modification modif;
	
	@Autowired
	Recherche rec;
	
	@Autowired
	Suppression suppr;
	
	boolean firstRun = true;
	
	/*Permet de peupler la base avec des données de test*/
	@Transactional
	void populate(){
		Societe aviva = new Societe();
    	aviva.setAdresse("impasse Bernard Madoff");
    	aviva.setRaisonSociale("Aviva SA");
    	aviva.setNumSiret("1324");
    	
    	c.societe(aviva);
    	
    	Societe total = new Societe();
    	total.setAdresse("Luxembourg");
    	total.setRaisonSociale("TOTAL SA");
    	total.setNumSiret("852147");
    	
    	c.societe(total);
    	

	}
	
	
	@RequestMapping(value = "/cherchersociete", method = RequestMethod.GET )
	public ModelAndView vehicule(	@RequestParam(value="pid", required=false) String pid,
									@RequestParam(value="adr", required=false) String adr, 
									@RequestParam(value="rs", required=false) String rs,
									@RequestParam(value="srt", required=false) String srt,
									ModelMap model) {
		
		/*On peuple la base lors de la première exécution seulement*/
		if(firstRun){
			firstRun = false;
			populate();	
		}

		CriteresSociete crtSte = new CriteresSociete();
		if(pid != null){
			crtSte.addCritere(CriteresSociete.ID_CLE, pid);
		}
		else if(adr != null){
			crtSte.addCritere(CriteresSociete.ADRESSE_CLE, adr);
		}
		else if(rs != null){
			crtSte.addCritere(CriteresSociete.RAISON_SOCIALE_CLE, rs);
		}
		else if(srt != null){
			crtSte.addCritere(CriteresSociete.NUMSIRET_CLE, srt);
		}
		
		List<Societe> mySociete = rec.chercherSociete(crtSte);
		ModelAndView myModel = new ModelAndView("listeSocietes");
		model.addAttribute("societemodel",new Societe());
		myModel.addObject("societes", mySociete);
		return myModel;
	}
	
	@RequestMapping(value = "/cherchersociete", method = RequestMethod.POST )
	public ModelAndView recherchersociete(@ModelAttribute("societemodel")Societe societe, ModelMap model) {
		if(firstRun){
			firstRun = false;
			populate();	
		}
		ModelAndView myModel = new ModelAndView("redirect:/cherchersociete");
		if(societe.hasAddresse())
			myModel.addObject("adr", societe.getAdresse());
		if(societe.hasRaisonSociale())
			myModel.addObject("rs", societe.getRaisonSociale());
		if(societe.hasNumSiret())
			myModel.addObject("srt", societe.getNumSiret());
		return myModel;
	}
	
	@RequestMapping(value = "/creersociete", method = RequestMethod.GET )
	   public ModelAndView formulaireVehicule(ModelMap model) {
		 ModelAndView myModel = new ModelAndView("formSociete");//nom prochain JSP
		   model.addAttribute("societemodel",new Societe());
		   myModel.addObject("action", "/docreersociete");
	      return myModel;
	   }

	@RequestMapping(value = "/docreersociete", method = RequestMethod.POST)
	public ModelAndView addSociete(@ModelAttribute("societemodel")Societe societe, 
			ModelMap model) {

		c.societe(societe);//on enregistre la societe
		
		ModelAndView myModel = new ModelAndView("redirect:/cherchersociete");
		//myModel.addObject("ns", vehicule.getNumSerie());
		return myModel;
	}
	
	@RequestMapping(value = "/editersociete", method = RequestMethod.POST )
	   public ModelAndView editSociete(@ModelAttribute("societemodel")Societe societe,
			   ModelMap model) {
		 ModelAndView myModel = new ModelAndView("formSociete");//nom prochain JSP
		   model.addAttribute("societemodel",societe);
		   myModel.addObject("action", "/doeditsociete");
	      return myModel;
	   }
	
	@RequestMapping(value = "/doeditsociete", method = RequestMethod.POST)
	public ModelAndView majSociete(@ModelAttribute("societemodel")Societe societe, 
			ModelMap model) {

		modif.societe(societe);//on met à jour la societe
		
		ModelAndView myModel = new ModelAndView("redirect:/cherchersociete");
		return myModel;
	}
	
	@RequestMapping(value = "/supprimersociete", method = RequestMethod.POST)
	public ModelAndView delVehicule(@ModelAttribute("societemodel")Societe societe, 
			ModelMap model) {

		suppr.societe(societe);//on supprime la societe
		
		ModelAndView myModel = new ModelAndView("redirect:/cherchersociete");
		return myModel;
	}

}
