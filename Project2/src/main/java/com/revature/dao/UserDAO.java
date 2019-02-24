package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.User;

public interface UserDAO {
	// DML
	public boolean insertUser(User user);
	public User updateUser(User user);
	public User deleteUser(User user);
	
	// DQL
	public User getUser(int userId);
	public User getUser(String username);
	public ArrayList<User> getAllUsers();
	
	// DML
	public boolean insertCredentials(String username, String password);
	public boolean updateCredentials(String username, String password);
	public boolean deleteCredentials(String username);
	
	// DCL
	public boolean grantDBPermissions(String username);
	public boolean revokeDBPermissions(String username);
	
	public String hashPassword(String username, String password);
	public User attemptAuthentication(String username, String password);

}
