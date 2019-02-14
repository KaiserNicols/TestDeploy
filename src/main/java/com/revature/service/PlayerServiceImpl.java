package com.revature.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.PlayerDAOImpl;
import com.revature.model.Player;

public class PlayerServiceImpl implements PlayerService {
	
	private static PlayerService playerService;
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			return getPlayer("admin");
		}
		if (request.getMethod().equals("POST")){
			try {
				Player logPlayer = null;
				logPlayer = mapper.readValue(request.getReader(), Player.class);
				final String username = logPlayer.getUsername();
				final String password = logPlayer.getPassword();
				Player attempting = attemptAuthentication(username, password);
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
		}
		
		
		return null;
	}
	
	public Player attemptAuthentication(String username, String password) {
		return PlayerDAOImpl.getPlayerDAO().attemptAuthentication(username, password);
	}
	public Player getPlayer(String username) {
		return PlayerDAOImpl.getPlayerDAO().getPlayer(username);
	}
	

}
