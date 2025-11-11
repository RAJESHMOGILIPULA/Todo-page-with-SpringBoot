package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	
	//get and post are handled 
	//mapping to login page
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String itsGoToWelcomePage(ModelMap model) {
		model.put("name",getLoggedInUsername());
		return "welcome";
	}
	public String getLoggedInUsername() {
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
