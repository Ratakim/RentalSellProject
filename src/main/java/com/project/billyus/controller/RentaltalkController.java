package com.project.billyus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller("rentaltalkController")
@RequestMapping("/rentaltalk")
public class RentaltalkController {
	
	private static final Logger logger = LoggerFactory.getLogger(RentaltalkController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/rentaltalkProcess.do", method = RequestMethod.GET)
	public ModelAndView loginProcess(HttpServletRequest request) {
		
		
		HttpSession session = request.getSession();
		
		String userInfoId = (String) session.getAttribute("userInfoId");
		
		ModelAndView mav = new ModelAndView();
		if (userInfoId != null) {
			logger.info("Welcome " + userInfoId);
			mav.setViewName("/test/chat");
		} else {
			logger.info("denied " + userInfoId);
			mav.setViewName("/user/loginForm.go");
		}
		
		return mav;
	}
	
	
}