package fr.istic.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*Controleur principal (page d'accueil)*/
@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET )
	public ModelAndView vehicule(ModelMap model) {
	
		ModelAndView myModel = new ModelAndView("index");
		return myModel;
	}
}
