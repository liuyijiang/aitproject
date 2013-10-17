package com.ait.gb.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserServiceController {
   
	public static final Logger logger = LoggerFactory.getLogger(UserServiceController.class); 
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		logger.trace("======1111trace");  
		 logger.debug("======222debug");  
		 logger.info("======333info");  
		 logger.warn("======444warn");  
		 logger.error("======555error");
		return new ModelAndView("index.jsp");
	}
	
}

	