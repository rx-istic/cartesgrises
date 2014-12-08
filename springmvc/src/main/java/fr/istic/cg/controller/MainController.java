package fr.istic.cg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET )
	public ModelAndView vehicule(ModelMap model) {
		
		List<String> mylinks = new ArrayList<String>();
		
		mylinks.add("/cherchervehicules");
		mylinks.add("/formulairevehicule");
		mylinks.add("/cherchersociete");
		
		
		ModelAndView myModel = new ModelAndView("index");
		myModel.addObject("links", mylinks);
		
		return myModel;
	}
}
