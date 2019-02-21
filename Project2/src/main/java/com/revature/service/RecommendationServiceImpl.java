package com.revature.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.RecommendationDAOImpl;
import com.revature.dao.UserDAOImpl;
import com.revature.model.Recommendation;

public class RecommendationServiceImpl implements RecommendationService{
	
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			//A user can retrieve all of their recommended movies
			//http://54.145.242.129:8080/Project2/rest/rec/all
			if (request.getRequestURI().contains("all")) {
				HttpSession session = request.getSession();
				int id = (int) session.getAttribute("id");
				return getAllRecommendations(id);
			}
			//http://54.145.242.129:8080/Project2/rest/rec/entirelist
			if (request.getRequestURI().contains("entirelist")) {
				return getAllRecommendations();
			}
			
		}
		
		
		return null;
	}
	
	public ArrayList<Recommendation> getAllRecommendations(int userId){
		return RecommendationDAOImpl.getRecommendationDAO().getAllRecommendations(userId);
	}
	public ArrayList<Recommendation> getAllRecommendations() {
		return RecommendationDAOImpl.getRecommendationDAO().getAllRecommendations();
	}

}