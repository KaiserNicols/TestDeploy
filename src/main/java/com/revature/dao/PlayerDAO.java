package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Player;

public interface PlayerDAO {
	// DML
	public Player insertPlayer(Player player);
	public Player updatePlayer(Player player);
	public Player deletePlayer(Player player);
	
	// DQL
	public Player getPlayer(int playerId);
	public Player getPlayer(String username);
	public ArrayList<Player> getAllPlayers();
	
	// DML
	public boolean insertCredentials(String username, String password);
	public boolean updateCredentials(String username, String password);
	public boolean deleteCredentials(String username);
	
	// DCL
	public boolean grantDBPermissions(String username);
	public boolean revokeDBPermissions(String username);
	
	public String hashPassword(String username, String password);
	
	//Kale: Adding the attemptAuthentication() method
	public Player attemptAuthentication(String username, String password);

}
