package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAOImpl;
import com.revature.model.User;

public class UserServiceImpl implements UserService {
	
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			if (request.getRequestURI().contains("all")) {
				return getAllUsers();
			}
			try {
				HttpSession session = request.getSession();
				int id = (int) session.getAttribute("id");
				return getUser(id);
			}catch(NullPointerException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		if (request.getMethod().equals("POST")){
			if (request.getRequestURI().contains("logout")) {
				try {
					UserDAOImpl.getUserDAO().logout(request, response);
					//String loginUrl = "http://localhost:8080/ExpenseReimbursement/html/Login.html";
					//response.setHeader("Location", loginUrl);
					response.sendRedirect("https://www.google.com/");
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				User logPlayer = null;
				logPlayer = mapper.readValue(request.getReader(), User.class);
				final String username = logPlayer.getUsername();
				final String password = logPlayer.getPassword();
				User attempting = attemptAuthentication(username, password);
				if (attempting != null) {
					HttpSession session = request.getSession();
					session.setAttribute("username", attempting.getUsername());
					session.setAttribute("id", attempting.getId());
					String url = "https://www.google.com/";
					response.setHeader("Location", url);
					return attempting;
					}
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
		return null; // TODO: Remove this later
		}
		return null;
	}
	
	public User attemptAuthentication(String username, String password) {
		return UserDAOImpl.getUserDAO().attemptAuthentication(username, password);
	}
	public User getUser(String username) {
		return UserDAOImpl.getUserDAO().getUser(username);
	}
	public User getUser(int id) {
		return UserDAOImpl.getUserDAO().getUser(id);
	}
	public ArrayList<User> getAllUsers() {
		return UserDAOImpl.getUserDAO().getAllUsers();
	}
	

}
