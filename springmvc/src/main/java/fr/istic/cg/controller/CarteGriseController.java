package fr.istic.cg.controller;

import java.util.Date;
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

import fr.istic.cg.donnees.CriteresCarteGrise;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Modification;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.metier.Suppression;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.ElementHistorique;
import fr.istic.cg.persistance.Particulier;
import fr.istic.cg.persistance.Vehicule;

@Controller
public class CarteGriseController {

	@Autowired
	Creation c;

	@Autowired
	Modification modif;

	@Autowired
	Recherche rec;

	@Autowired
	Suppression suppr;

	boolean firstRun = true;

	@Transactional
	void populate(){
		Particulier batman = new Particulier();
		batman.setAdresse("Gotham");
		batman.setNom("Wayne");
		batman.setPrenom("Bruce");
		c.particulier(batman);
		
		Particulier ouioui = new Particulier();
		ouioui.setAdresse("Pays des jouets");
		ouioui.setNom("Oui");
		ouioui.setPrenom("Oui");
		c.particulier(ouioui);
		
		Vehicule v = new Vehicule();
		v.setNumSerie("31337");
		v.setMarque("Waynes Motors");
		v.setType("Justicier");
		v.setModele("Batmobile");

		c.vehicule(v);
		
		Vehicule v2 = new Vehicule();
		v2.setNumSerie("0101");
		v2.setMarque("Cabriolet");
		v2.setType("Jouet");
		v2.setModele("Rouge");
		
		c.vehicule(v2);
		
		ElementHistorique elh = new ElementHistorique();
    	elh.setDateDebut(new Date(1997, 12, 25));
    	Date now = new Date(System.currentTimeMillis());
    	elh.setDateFin(now);
    	elh.setRefProrietaire(batman);
    	
    	c.elementHistorique(elh);
    	
    	ElementHistorique elh2 = new ElementHistorique();
    	elh2.setDateDebut(new Date(1995, 10, 3));
    	Date now2 = new Date(System.currentTimeMillis());
    	elh2.setDateFin(now);
    	elh2.setRefProrietaire(ouioui);
    	
    	c.elementHistorique(elh2);
    	
    	
    	CarteGrise cg = new CarteGrise();
    	cg.setImmatriculation("D4RK_KN1G7H");
    	cg.setRefVehicule(v);
    	cg.addHistorique(elh);
    	
    	c.carteGrise(cg);
    	
    	CarteGrise cg2 = new CarteGrise();
    	cg2.setImmatriculation("Oui-Oui");
    	cg2.setRefVehicule(v2);
    	cg2.addHistorique(elh2);
    	
    	c.carteGrise(cg2);
	}

	@RequestMapping(value = "/cherchercg", method = RequestMethod.GET )
	public ModelAndView cg(	@RequestParam(value="im", required=false) String im, 
			ModelMap model) {
		
		if(firstRun){
			firstRun = false;
			populate();	
		}

		CriteresCarteGrise crtCG = new CriteresCarteGrise();
		if(im != null){
			crtCG.addCritere(CriteresCarteGrise.IMMATRICULATION_CLE, im);
		}

		List<CarteGrise> myCG = rec.chercherCarteGrise(crtCG);
		ModelAndView myModel = new ModelAndView("listeCartesGrises");
		model.addAttribute("cgmodel",new CarteGrise());
		myModel.addObject("cartesGrises", myCG);
		myModel.addObject("action", "/cherchercg");
		return myModel;
	}

	@RequestMapping(value = "/cherchercg", method = RequestMethod.POST )
	public ModelAndView recherchercg(@ModelAttribute("cgmodel")CarteGrise cg, ModelMap model) {
		if(firstRun){
			firstRun = false;
			populate();	
		}
		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");
		if(cg.hasImmatriculation())
			myModel.addObject("im", cg.getImmatriculation());
		return myModel;
	}

	@RequestMapping(value = "/creercg", method = RequestMethod.GET )
	public ModelAndView formulaireCG(ModelMap model) {
		ModelAndView myModel = new ModelAndView("formCG");//nom prochain JSP
		model.addAttribute("cgmodel",new CarteGrise());
		myModel.addObject("action", "/docreercg");
		return myModel;
	}

	@RequestMapping(value = "/docreercg", method = RequestMethod.POST)
	public ModelAndView addCG(@ModelAttribute("cgmodel")CarteGrise cg, 
			ModelMap model) {

		c.carteGrise(cg);

		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");
		return myModel;
	}

	@RequestMapping(value = "/editercg", method = RequestMethod.POST )
	public ModelAndView editCG(@ModelAttribute("cgmodel")CarteGrise cg,
			ModelMap model) {
		ModelAndView myModel = new ModelAndView("formCG");//nom prochain JSP
		model.addAttribute("cgmodel",cg);
		myModel.addObject("action", "/doeditcg");
		return myModel;
	}

	@RequestMapping(value = "/doeditcg", method = RequestMethod.POST)
	public ModelAndView majVehicule(@ModelAttribute("cgmodel")CarteGrise cg, 
			ModelMap model) {

		modif.carteGrise(cg);

		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");
		
		return myModel;
	}

	@RequestMapping(value = "/supprimercg", method = RequestMethod.POST)
	public ModelAndView delCG(@ModelAttribute("cgmodel")CarteGrise cg, 
			ModelMap model) {

		suppr.carteGrise(cg);

		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");
		return myModel;
	}
}
