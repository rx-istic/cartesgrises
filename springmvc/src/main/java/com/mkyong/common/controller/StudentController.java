package com.mkyong.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.Student;

@Controller
public class StudentController {

   @RequestMapping(value = "/student", method = RequestMethod.GET )
   public String student(ModelMap model) {
	   model.addAttribute("studentmodel",new Student());
      return "student";
   }
   
   @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute("studentmodel")Student student, 
   ModelMap model) {
      //TODO add here the communication with the business part
	   
      return "hello";
   }
}