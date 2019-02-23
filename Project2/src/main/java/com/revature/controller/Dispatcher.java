package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.RecommendationService;
import com.revature.service.RecommendationServiceImpl;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

public class Dispatcher {
	
	private Dispatcher() {}
	
	private static final UserService userService = new UserServiceImpl();
	private static final RecommendationService recService = new RecommendationServiceImpl();

	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getRequestURI().contains("user")) {
			
			return userService.process(request, response);
		}
		else if (request.getRequestURI().contains("rec")) {
			return recService.process(request, response);
		}else 
			return "Not yet implemented";
		
	}
	
	
	

}
