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
import fr.istic.cg.persistance.Societe;
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
		
		Societe shinra = new Societe();
		shinra.setAdresse("Midgard");
		shinra.setNumSiret("9898");
		shinra.setRaisonSociale("Shinra Corp");
		c.societe(shinra);
		
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
		
		Vehicule v3 = new Vehicule();
		v3.setNumSerie("5555");
		v3.setMarque("Shinra Motors");
		v3.setType("Moto");
		v3.setModele("Hardy Daytona");

		c.vehicule(v3);

		ElementHistorique elh = new ElementHistorique();
		elh.setDateDebut(new Date(1997-1900, 12, 25));
		Date now = new Date(System.currentTimeMillis());
		elh.setDateFin(now);
		elh.setRefProrietaire(batman);

		c.elementHistorique(elh);

		ElementHistorique elh2 = new ElementHistorique();
		elh2.setDateDebut(new Date(1995-1900, 10, 3));
		Date now2 = new Date(System.currentTimeMillis());
		elh2.setDateFin(now);
		elh2.setRefProrietaire(ouioui);

		c.elementHistorique(elh2);
		
		ElementHistorique elh3 = new ElementHistorique();
		elh3.setDateDebut(new Date(1888-1900, 5, 10));
		Date now3 = new Date(System.currentTimeMillis());
		elh3.setDateFin(now);
		elh3.setRefProrietaire(shinra);

		c.elementHistorique(elh3);


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
		
		CarteGrise cg3 = new CarteGrise();
		cg3.setImmatriculation("7777");
		cg3.setRefVehicule(v3);
		cg3.addHistorique(elh3);

		c.carteGrise(cg3);
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


	@RequestMapping(value = "/cgdetails", method = RequestMethod.GET )
	public ModelAndView detailsCarteGrise(@RequestParam(value="cg", required=false) String im,
			ModelMap model) {

		CriteresCarteGrise crtCG = new CriteresCarteGrise();
		if(im != null){
			crtCG.addCritere(CriteresCarteGrise.IMMATRICULATION_CLE, im);
		}
		List<CarteGrise> myCG = rec.chercherCarteGrise(crtCG);

		ModelAndView myModel = null;
		if(myCG.size() > 1){
			myModel = new ModelAndView("redirect:/cherchercg");
			if(im != null && im.length() > 0){
				myModel.addObject("im", im);
			}
		}else if (myCG.size() == 0){
			myModel = new ModelAndView("redirect:/cherchercg");
		}else{
			myModel = new ModelAndView("cgDetails");
			CarteGrise carteGrise = myCG.get(0);
			myModel.addObject("carteGrise", carteGrise);
			myModel.addObject("vehicule",carteGrise.getRefVehicule());

			myModel.addObject("historique",carteGrise.getHistorique());
		}

		return myModel;
	}
}
