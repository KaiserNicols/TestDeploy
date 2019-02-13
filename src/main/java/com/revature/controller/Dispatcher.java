package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.PlayerService;
import com.revature.service.PlayerServiceImpl;

public class Dispatcher {
	
	private Dispatcher() {}
	
	private static final PlayerService playerService = new PlayerServiceImpl();

	public static Object process(HttpServletRequest request, HttpServletResponse response) {
/*
 * if (request.getRequestURI().contains("employee"))
			return employeeService.process(request, response);
		else if (request.getRequestURI().contains("reimbursement"))
			return rebService.process(request, response);
		else 
			return "Not yet implemented";
 */
		
		if (request.getRequestURI().contains("player")) {
			return playerService.process(request, response);
		}
		return null;
	}
	
	
	

}
