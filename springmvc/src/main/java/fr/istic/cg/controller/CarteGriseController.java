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
import fr.istic.cg.donnees.CriteresParticulier;
import fr.istic.cg.donnees.CriteresSociete;
import fr.istic.cg.donnees.CriteresVehicule;
import fr.istic.cg.metier.Creation;
import fr.istic.cg.metier.Modification;
import fr.istic.cg.metier.Recherche;
import fr.istic.cg.metier.Suppression;
import fr.istic.cg.persistance.CarteGrise;
import fr.istic.cg.persistance.ElementHistorique;
import fr.istic.cg.persistance.Particulier;
import fr.istic.cg.persistance.Proprietaire;
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
		elh2.setDateDebut(new Date(1995-1900, 10, 3));//necessaire car Date compte les annees a partir de 1900!
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

		List<CarteGrise> myCG = searchCartesGrises(im);
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
	public ModelAndView detailsCarteGrise(@RequestParam(value="im", required=false) String im,
			ModelMap model) {


		List<CarteGrise> myCG = searchCartesGrises(im);

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

	@RequestMapping(value = "/cgeditvehicule", method = {RequestMethod.GET} )
	public ModelAndView editVehiculeCarteGrise(
			@RequestParam(value="im", required=false) String im,
			@RequestParam(value="ns", required=false) String ns, 
			@RequestParam(value="mq", required=false) String mq,
			@RequestParam(value="md", required=false) String md,
			@RequestParam(value="tp", required=false) String tp,
			@ModelAttribute("vehiculemodel")Vehicule vehicule,
			ModelMap model) {

		if(im == null){
			return new ModelAndView("redirect:/cherchercg");
		}

		List<CarteGrise> myCG = searchCartesGrises(im);

		ModelAndView myModel = null;
		if(myCG.size() > 1){
			myModel = new ModelAndView("redirect:/cherchercg");
			if(im != null && im.length() > 0){
				myModel.addObject("im", im);
			}
		}else if (myCG.size() == 0){
			myModel = new ModelAndView("redirect:/cherchercg");
		}else{
			//recherche de vehicules

			List<Vehicule> myVehicules = searchVehicules(ns, mq, md, tp);

			myModel = new ModelAndView("cgDetailsVehicule");
			CarteGrise carteGrise = myCG.get(0);
			myModel.addObject("carteGrise", carteGrise);
			myModel.addObject("vehicules", myVehicules);
			myModel.addObject("action", "/cgsearchvehicule");
			model.addAttribute("vehiculemodel",new Vehicule());

		}

		return myModel;
	}

	@RequestMapping(value = "/cgsearchvehicule", method = {RequestMethod.POST} )
	public ModelAndView searchVehiculeCarteGrise(@RequestParam(value="im", required=false) String im,
			@ModelAttribute("vehiculemodel")Vehicule vehicule,
			ModelMap model) {

		if(im == null){
			return new ModelAndView("redirect:/cherchercg");
		}

		ModelAndView myModel = new ModelAndView("redirect:/cgeditvehicule");
		myModel.addObject("im", im);

		if(vehicule.hasNumSerie())
			myModel.addObject("ns", vehicule.getNumSerie());
		if(vehicule.hasMarque())
			myModel.addObject("mq", vehicule.getMarque());
		if(vehicule.hasModele())
			myModel.addObject("md", vehicule.getModele());
		if(vehicule.hasType())
			myModel.addObject("tp", vehicule.getType());
		return myModel;
	}


	@RequestMapping(value = "/cgassociervehicule", method = {RequestMethod.POST} )
	public ModelAndView linkVehiculeCarteGrise(@RequestParam(value="im", required=false) String im,
			@RequestParam(value="vcl", required=false) String vcl,
			ModelMap model) {

		if(im == null){
			return new ModelAndView("redirect:/cherchercg");
		}

		ModelAndView myModel = new ModelAndView("redirect:/cgdetails");
		myModel.addObject("im", im);

		if(vcl == null){
			return myModel;
		}


		List<CarteGrise> myCG = searchCartesGrises(im);
		if(myCG.size() == 1){
			CarteGrise carteGrise = myCG.get(0);

			List<Vehicule> myVehicules = searchVehicules(vcl);

			if(myVehicules.size() == 1){//on peut associer le vehicule
				carteGrise.setRefVehicule(myVehicules.get(0));
				modif.carteGrise(carteGrise);//mise a jour de la carte grise
			}
		}


		return myModel;
	}

	@RequestMapping(value = "/cgremovevehicule", method = {RequestMethod.POST} )
	public ModelAndView unlinkVehiculeCarteGrise(@RequestParam(value="im", required=false) String im,
			ModelMap model) {

		if(im == null){
			return new ModelAndView("redirect:/cherchercg");
		}


		List<CarteGrise> myCG = searchCartesGrises(im);
		if(myCG.size() == 1){
			CarteGrise carteGrise = myCG.get(0);

			carteGrise.setRefVehicule(null);
			modif.carteGrise(carteGrise);//mise a jour de la carte grise

		}

		ModelAndView myModel = new ModelAndView("redirect:/cgdetails");
		myModel.addObject("im", im);
		return myModel;
	}

	@RequestMapping(value = "/cgaddproprietaire", method = {RequestMethod.GET} )
	public ModelAndView addProprietaireCarteGrise(@RequestParam(value="im", required=false) String im,
			ModelMap model) {

		if(im == null){
			return new ModelAndView("redirect:/cherchercg");
		}

		List<CarteGrise> myCG = searchCartesGrises(im);
		if(myCG.size() != 1){
			return new ModelAndView("redirect:/cherchercg");
		}

		ModelAndView myModel = new ModelAndView("cgDetailsAjoutProprietaire");
		myModel.addObject("im", im);
		
		CarteGrise carteGrise = myCG.get(0);
		myModel.addObject("carteGrise", carteGrise);
		myModel.addObject("vehicule", carteGrise.getRefVehicule());
		myModel.addObject("particuliers", searchParticuliers(null));
		myModel.addObject("societes", searchSocietes(null));
		
		return myModel;
	}
	
	@RequestMapping(value = "/cgdoaddproprietaire", method = {RequestMethod.POST} )
	public ModelAndView doAddProprietaireCarteGrise(@RequestParam(value="im", required=false) String im,
			@RequestParam(value="dDebut", required=false) String dDebut,
			@RequestParam(value="dFin", required=false) String dFin,
			@RequestParam(value="pid", required=false) String pid,
			ModelMap model) {

		if(im == null){
			return new ModelAndView("redirect:/cherchercg");
		}
		
		ModelAndView errorModel = new ModelAndView("redirect:/cgaddproprietaire");
		errorModel.addObject("im", im);
		
		if(pid == null || dDebut == null || dFin == null){
			
			return errorModel;
		}
		
		List<CarteGrise> myCG = searchCartesGrises(im);
		if(myCG.size() != 1){
			return errorModel;
		}
		
		CarteGrise carteGrise = myCG.get(0);
		
		Proprietaire proprio = null;
		List<Particulier> myCGpart = searchParticuliers(pid);
		if(myCGpart.size() == 1){
			proprio = myCGpart.get(0);
		}else{
			List<Societe> myCGste = searchSocietes(pid);
			if(myCGste.size() == 1){
				proprio = myCGste.get(0);
			}
		}
		
		if(proprio == null){
			return errorModel;
		}
		
		Date dateDebut = new Date(dDebut);
		Date dateFin = new Date(dFin);
		
		ElementHistorique elh = new ElementHistorique();
		elh.setDateDebut(dateDebut);
		elh.setDateFin(dateFin);
		elh.setRefProrietaire(proprio);
		
		c.elementHistorique(elh);
		carteGrise.addHistorique(elh);
		modif.carteGrise(carteGrise);
		
		ModelAndView myModel = new ModelAndView("redirect:/cgdetails");
		myModel.addObject("im", im);
		
		return myModel;
	}

	private List<CarteGrise> searchCartesGrises(String immatriculation){
		CriteresCarteGrise crtCG = new CriteresCarteGrise();
		if(immatriculation != null){
			crtCG.addCritere(CriteresCarteGrise.IMMATRICULATION_CLE, immatriculation);
		}
		return rec.chercherCarteGrise(crtCG);
	}

	private List<Vehicule> searchVehicules(String numSerie, String marque, String modele, String type){
		CriteresVehicule crtVcl = new CriteresVehicule();
		if(numSerie != null){
			crtVcl.addCritere(CriteresVehicule.NUMSERIE_CLE, numSerie);
		}
		else if(marque!= null){
			crtVcl.addCritere(CriteresVehicule.MARQUE_CLE, marque);
		}
		else if(modele != null){
			crtVcl.addCritere(CriteresVehicule.MODELE_CLE, modele);
		}
		else if(type != null){
			crtVcl.addCritere(CriteresVehicule.TYPE_CLE, type);
		}

		return rec.chercherVehicule(crtVcl);
	}

	private List<Vehicule> searchVehicules(String numSerie){
		return searchVehicules(numSerie, null, null, null);
	}
	
	private List<Particulier> searchParticuliers(String pid){
		CriteresParticulier crtPcl = new CriteresParticulier();
		if(pid != null){
			crtPcl.addCritere(CriteresParticulier.ID_CLE, pid);
		}
		
		return rec.chercherParticulier(crtPcl);
	}
	
	private List<Societe> searchSocietes(String pid){
		CriteresSociete crtSte = new CriteresSociete();
		if(pid != null){
			crtSte.addCritere(CriteresParticulier.ID_CLE, pid);
		}
		
		return rec.chercherSociete(crtSte);
	}
}
