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

import fr.istic.cg.donnees.CriteresParticulier;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Modification;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.metier.Suppression;
import fr.istic.cg.persistance.Particulier;

@Controller
public class ParticulierController {

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
		Particulier cloud = new Particulier();
		cloud.setAdresse("Kalm");
		cloud.setNom("Strife");
		cloud.setPrenom("Cloud");
		c.particulier(cloud);

		Particulier superman = new Particulier();
		superman.setAdresse("Metropolis");
		superman.setNom("Kent");
		superman.setPrenom("Clark");
		c.particulier(superman);    	

	}

	
	@RequestMapping(value = "/chercherparticulier", method = RequestMethod.GET )
	public ModelAndView particulier(@RequestParam(value="pid", required=false) String pid,
									@RequestParam(value="adr", required=false) String adr, 
									@RequestParam(value="nm", required=false) String nom,
									@RequestParam(value="pnom", required=false) String pnom,
									ModelMap model) {
		if(firstRun){
			firstRun = false;
			populate();	
		}

		CriteresParticulier crtPcl = new CriteresParticulier();
		if(pid != null){
			crtPcl.addCritere(CriteresParticulier.ID_CLE, pid);
		}
		if(adr != null){
			crtPcl.addCritere(CriteresParticulier.ADRESSE_CLE, adr);
		}
		else if(nom != null){
			crtPcl.addCritere(CriteresParticulier.NOM_CLE, nom);
		}
		else if(pnom != null){
			crtPcl.addCritere(CriteresParticulier.PRENOM_CLE, pnom);
		}
		
		List<Particulier> myParticuliers = rec.chercherParticulier(crtPcl);
		ModelAndView myModel = new ModelAndView("listeParticuliers");
		model.addAttribute("particuliermodel",new Particulier());
		myModel.addObject("particuliers", myParticuliers);
		return myModel;
	}
	
	@RequestMapping(value = "/chercherparticulier", method = RequestMethod.POST )
	public ModelAndView rechercherparticulier(@ModelAttribute("particuliermodel")Particulier particulier, ModelMap model) {
		if(firstRun){
			firstRun = false;
			populate();	
		}
		ModelAndView myModel = new ModelAndView("redirect:/chercherparticulier");
		if(particulier.hasAddresse())
			myModel.addObject("adr", particulier.getAdresse());
		if(particulier.hasNom())
			myModel.addObject("nm", particulier.getNom());
		if(particulier.hasPrenom())
			myModel.addObject("pnom", particulier.getPrenom());
		return myModel;
	}
	
	@RequestMapping(value = "/creerparticulier", method = RequestMethod.GET )
	   public ModelAndView formulaireParticulier(ModelMap model) {
		 ModelAndView myModel = new ModelAndView("formParticulier");//nom prochain JSP
		   model.addAttribute("particuliermodel",new Particulier());
		   myModel.addObject("action", "/docreerparticulier");
	      return myModel;
	   }

	@RequestMapping(value = "/docreerparticulier", method = RequestMethod.POST)
	public ModelAndView addSociete(@ModelAttribute("particuliermodel")Particulier particulier, 
			ModelMap model) {

		c.particulier(particulier);//on enregistre le particulier
		
		ModelAndView myModel = new ModelAndView("redirect:/chercherparticulier");
		//myModel.addObject("ns", vehicule.getNumSerie());
		return myModel;
	}
	
	@RequestMapping(value = "/editerparticulier", method = RequestMethod.POST )
	   public ModelAndView editParticulier(@ModelAttribute("particuliermodel")Particulier particulier,
			   ModelMap model) {
		 ModelAndView myModel = new ModelAndView("formParticulier");//nom prochain JSP
		   model.addAttribute("particuliermodel",particulier);
		   myModel.addObject("action", "/doeditparticulier");
	      return myModel;
	   }
	
	@RequestMapping(value = "/doeditparticulier", method = RequestMethod.POST)
	public ModelAndView majParticulier(@ModelAttribute("particuliermodel")Particulier particulier, 
			ModelMap model) {

		modif.particulier(particulier);//on met à jour le particulier
		
		ModelAndView myModel = new ModelAndView("redirect:/chercherparticulier");
		return myModel;
	}
	
	@RequestMapping(value = "/supprimerparticulier", method = RequestMethod.POST)
	public ModelAndView delParticulier(@ModelAttribute("particuliermodel")Particulier particulier, 
			ModelMap model) {

		suppr.particulier(particulier);//on supprime le particulier
		
		ModelAndView myModel = new ModelAndView("redirect:/chercherparticulier");
		return myModel;
	}

}
