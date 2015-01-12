package fr.istic.cg.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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

	/*Permet de peupler la base avec des données de test*/
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
			@RequestParam(value="ns", required=false) String ns, 
			@RequestParam(value="mq", required=false) String mq,
			@RequestParam(value="md", required=false) String md,
			@RequestParam(value="tp", required=false) String tp,
			ModelMap model) {

		/*On peuple la base lors de la première exécution seulement*/
		if(firstRun){
			firstRun = false;
			populate();	
		}

		List<CarteGrise> myCG = searchCartesGrises(im, ns, mq, md, tp);
		
		HashMap<String, String> mypropietaires = new HashMap<String, String>();
		for(CarteGrise cg : myCG){
			mypropietaires.put(cg.getImmatriculation(), getLastProprietaire(cg.getHistorique()));
		}
		
		ModelAndView myModel = new ModelAndView("listeCartesGrises");
		model.addAttribute("cgmodel",new CarteGrise());
		myModel.addObject("cartesGrises", myCG);
		model.addAttribute("proprietaires", mypropietaires);
		myModel.addObject("action", "/cherchercg");
		return myModel;
	}

	/*Fonction utilitaire : récupère le proprietaire le plus récent de l'historique*/
	private String getLastProprietaire(Collection<ElementHistorique> historique) {
		
		ElementHistorique elret = null;
		for(ElementHistorique el : historique){
			if(elret == null){
				elret = el;
			}else
			{
				if(elret.getDateFin().before(el.getDateFin())){
					elret = el;
				}
			}
			
		}
		
		String retstr = "";
		
		if(elret != null && elret.getRefProrietaire() != null){
			if(elret.getRefProrietaire().getTypeProprietaire() == Proprietaire.TYPE_PARTICULIER){
				Particulier p = (Particulier) elret.getRefProrietaire();
				retstr = p.getNom()+ " " + p.getPrenom();
			}else if(elret.getRefProrietaire().getTypeProprietaire() == Proprietaire.TYPE_SOCIETE){
				Societe s = (Societe) elret.getRefProrietaire();
				retstr = s.getRaisonSociale() + " " + s.getNumSiret();
			}
		}
		
		return retstr;
	}

	/*Déclenché par la soumission du formulaire de recherche*/
	@RequestMapping(value = "/cherchercg", method = RequestMethod.POST )
	public ModelAndView recherchercg(@ModelAttribute("cgmodel")CarteGrise cg, 
			@RequestParam(value="ns", required=false) String ns, 
			@RequestParam(value="mq", required=false) String mq,
			@RequestParam(value="md", required=false) String md,
			@RequestParam(value="tp", required=false) String tp,
			ModelMap model) {
		if(firstRun){
			firstRun = false;
			populate();	
		}
		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");
		if(cg.hasImmatriculation()){
			myModel.addObject("im", cg.getImmatriculation());
		}
		if(ns != null && ns.length() > 0){
			myModel.addObject("ns", ns);
		}
		if(mq != null && mq.length() > 0){
			myModel.addObject("mq", mq);
		}
		if(md != null && md.length() > 0){
			myModel.addObject("md", md);
		}
		if(tp != null && tp.length() > 0){
			myModel.addObject("tp", tp);
		}
		return myModel;
	}

	/*Retourne le formulaire de création*/
	@RequestMapping(value = "/creercg", method = RequestMethod.GET )
	public ModelAndView formulaireCG(ModelMap model) {
		ModelAndView myModel = new ModelAndView("formCG");//nom prochain JSP
		model.addAttribute("cgmodel",new CarteGrise());
		myModel.addObject("action", "/docreercg");
		return myModel;
	}

	/*Déclenche la création de la carte grise (soumission du formulaire de création)*/
	@RequestMapping(value = "/docreercg", method = RequestMethod.POST)
	public ModelAndView addCG(@ModelAttribute("cgmodel")CarteGrise cg, 
			ModelMap model) {

		c.carteGrise(cg);

		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");
		return myModel;
	}

	/*retourne le formulaire d'édition*/
	@RequestMapping(value = "/editercg", method = RequestMethod.POST )
	public ModelAndView editCG(@ModelAttribute("cgmodel")CarteGrise cg,
			ModelMap model) {
		ModelAndView myModel = new ModelAndView("formCG");//nom prochain JSP
		model.addAttribute("cgmodel",cg);
		myModel.addObject("action", "/doeditcg");
		return myModel;
	}

	/*Valide les modifications apportées (à la soumission du formulaire d'édition)*/
	@RequestMapping(value = "/doeditcg", method = RequestMethod.POST)
	public ModelAndView majVehicule(@ModelAttribute("cgmodel")CarteGrise cg, 
			ModelMap model) {

		modif.carteGrise(cg);

		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");

		return myModel;
	}

	/*supprime une carte grise*/
	@RequestMapping(value = "/supprimercg", method = RequestMethod.POST)
	public ModelAndView delCG(@ModelAttribute("cgmodel")CarteGrise cg, 
			ModelMap model) {

		suppr.carteGrise(cg);

		ModelAndView myModel = new ModelAndView("redirect:/cherchercg");
		return myModel;
	}


	/*Affiche les détails d'une carte grise en particulier*/
	@RequestMapping(value = "/cgdetails", method = RequestMethod.GET )
	public ModelAndView detailsCarteGrise(@RequestParam(value="im", required=false) String im,
			@ModelAttribute("elementmodel")ElementHistorique ehmodel,
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
			
			model.addAttribute("elementmodel", ehmodel);
		}

		return myModel;
	}

	/*Formulaire permettant de choisir un véhicule à associer à la carte grise courante*/
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

	/*Recherche d'un véhicule depuis la vue d'association d'un véhicule*/
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


	/*Effectue l'association d'un véhicule à une carte grise*/
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

	/*Supprime l'association d'un véhicule et d'une carte grise*/
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

	/*Formulaire de choix d'un proprio pour une carte grise (element historique)*/
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
	
	/*Ajout d'un proprio pour une carte grise (element historique)*/
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

	/*Fonction utilitaire : renvoie toutes les cartes grises existantes*/
	private List<CarteGrise> searchCartesGrises(String immatriculation){
		return searchCartesGrises(immatriculation, null, null, null, null);
	}
	
	/*Fonction utilitaire : renvoie les cartes grises correspondant aux critères*/
	private List<CarteGrise> searchCartesGrises(String immatriculation, String numSerie, String marque, String modele, String type){
		CriteresCarteGrise crtCG = new CriteresCarteGrise();
		if(immatriculation != null){
			crtCG.addCritere(CriteresCarteGrise.IMMATRICULATION_CLE, immatriculation);
		}
		
		//on exclu les cartes grises dont les voitures ne répondent pas aux criteres de recherche
		List<CarteGrise> ret = rec.chercherCarteGrise(crtCG);
		ArrayList<CarteGrise> todel = new ArrayList<CarteGrise>();
		
		for(CarteGrise c : ret){
			Vehicule v = c.getRefVehicule();
			if(numSerie != null && numSerie.length() > 0){
				if(!v.getNumSerie().equals(numSerie)){
					todel.add(c);
					continue;
				}
			}
			
			if(marque != null && marque.length() > 0){
				if(!v.getMarque().equals(marque)){
					todel.add(c);
					continue;
				}
			}
			
			if(modele != null && modele.length() > 0){
				if(!v.getModele().equals(modele)){
					todel.add(c);
					continue;
				}
			}
			
			if(type != null && type.length() > 0){
				if(!v.getType().equals(type)){
					todel.add(c);
					continue;
				}
			}
		}
		
		for(CarteGrise c : todel){
			ret.remove(c);
		}
		
		return ret;
	}

	/*Fonction utilitaire : renvoie les vehicules correspondants aux critères*/
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

	/*Fonction utilitaire : renvoie tous les véhicules*/
	private List<Vehicule> searchVehicules(String numSerie){
		return searchVehicules(numSerie, null, null, null);
	}
	
	/*Fonction utilitaire : renvoie les particuliers correspondant au pid donné (tous si pid=null)*/
	private List<Particulier> searchParticuliers(String pid){
		CriteresParticulier crtPcl = new CriteresParticulier();
		if(pid != null){
			crtPcl.addCritere(CriteresParticulier.ID_CLE, pid);
		}
		
		return rec.chercherParticulier(crtPcl);
	}
	
	/*Fonction utilitaire : renvoie les societes correspondant au pid donné (tous si pid=null)*/
	private List<Societe> searchSocietes(String pid){
		CriteresSociete crtSte = new CriteresSociete();
		if(pid != null){
			crtSte.addCritere(CriteresParticulier.ID_CLE, pid);
		}
		
		return rec.chercherSociete(crtSte);
	}
	
	/*Supprime un element historique d ela carte grise*/
	@RequestMapping(value = "/supprimerelementhistorique", method = RequestMethod.POST)
	public ModelAndView delElementHistorique(@ModelAttribute("elementmodel")ElementHistorique ehmodel,
			@RequestParam(value="im", required=false) String im,
			ModelMap model) {

		List<CarteGrise> myCG = searchCartesGrises(im);
		if(myCG.size() == 1){
			CarteGrise carteGrise = myCG.get(0);
			
			for(ElementHistorique elh : carteGrise.getHistorique()){
				if(elh.getId() == ehmodel.getId()){
					carteGrise.getHistorique().remove(elh);
					modif.carteGrise(carteGrise);
					suppr.elementHistorique(elh);
					break;
				}
			}
		}	
		ModelAndView myModel = new ModelAndView("redirect:/cgdetails");
		myModel.addObject("im", im);
		return myModel;
	}
}
