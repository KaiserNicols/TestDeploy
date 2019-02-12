package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher {
	
	private Dispatcher() {
			
		}

	public static Object process(HttpServletRequest request, HttpServletResponse response) {
/*
 * if (request.getRequestURI().contains("employee"))
			return employeeService.process(request, response);
		else if (request.getRequestURI().contains("reimbursement"))
			return rebService.process(request, response);
		else 
			return "Not yet implemented";
 */
		return null;
	}
	
	
	

}
