package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServiceImpl implements UserService {
//	
//	private static PlayerService playerService;
//	private final ObjectMapper mapper = new ObjectMapper();
//
	@Override
	public Object process(HttpServletRequest request, HttpServletResponse response) {
//		if (request.getMethod().equals("GET")) {
//			return getPlayer("admin");
//		}
//		if (request.getMethod().equals("POST")){
//			try {
//				User logPlayer = null;
//				logPlayer = mapper.readValue(request.getReader(), User.class);
//				final String username = logPlayer.getUsername();
//				final String password = logPlayer.getPassword();
//				User attempting = attemptAuthentication(username, password);
//				if (attempting != null) {
//					HttpSession session = request.getSession();
//					session.setAttribute("username", attempting.getUsername());
//					session.setAttribute("id", attempting.getId());
//					String url = "https://www.google.com/";
//					response.setHeader("Location", url);
//					return attempting;
//					}
//			}catch (JsonParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		return null; // TODO: Remove this later
		}
//		
//		
//		return null;
//	}
//	
//	public User attemptAuthentication(String username, String password) {
//		return UserDAOImpl.getPlayerDAO().attemptAuthentication(username, password);
//	}
//	public User getPlayer(String username) {
//		return UserDAOImpl.getPlayerDAO().getPlayer(username);
//	}
//	

}
