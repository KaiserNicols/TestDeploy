package com.revature.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.RecommendationDAOImpl;
import com.revature.dao.UserDAOImpl;
import com.revature.model.Recommendation;
import com.revature.model.User;

public class RecommendationServiceImpl implements RecommendationService{
	
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			//A user can retrieve all of their recommended movies"
			//http://54.145.242.129:8080/Project2/rest/rec/all
			if (request.getRequestURI().contains("all")) {
				HttpSession session = request.getSession();
				int id = (int) session.getAttribute("id");
				System.out.println(id);
				return getAllRecommendations(id);
			}
			//http://54.145.242.129:8080/Project2/rest/rec/entirelist
			if (request.getRequestURI().contains("entirelist")) {
				return getAllRecommendations();
			}
			
		}
		if (request.getMethod().equals("POST")) {
			if (request.getRequestURI().contains("all")) {
				User logPlayer = null;
				try {
					logPlayer = mapper.readValue(request.getReader(), User.class);
					final String username = logPlayer.getUsername();
					User currentUser = new User();
					currentUser = getUser(username);
					System.out.println(currentUser.getId());
					return getAllRecommendations(currentUser.getId());
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (request.getRequestURI().contains("submit")) {
				Recommendation newRec = null;
				User logPlayer = null;
				//http://54.145.242.129:8080/Project2/rest/rec/submit
				try {
					logPlayer = mapper.readValue(request.getReader(), User.class);
					newRec = mapper.readValue(request.getReader(), Recommendation.class);
					final String username = logPlayer.getUsername();
					int userId = logPlayer.getId();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					String nowString = now.toString();
					newRec.setId(userId);
					newRec.setDate(nowString);
					return insertRecommendation(newRec);
				}catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
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
	public User getUser(String username) {
		return UserDAOImpl.getUserDAO().getUser(username);
	}
	public Recommendation insertRecommendation(Recommendation recommendation) {
		return RecommendationDAOImpl.getRecommendationDAO().insertRecommendation(recommendation);
	}
	

}