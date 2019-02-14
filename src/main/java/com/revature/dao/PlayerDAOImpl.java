package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.revature.model.Player;
import com.revature.util.ConnectionUtil;

public class PlayerDAOImpl implements PlayerDAO {
	
	private static Logger logger = Logger.getLogger(PlayerDAOImpl.class);
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	// Singleton setup
	private static PlayerDAOImpl instance;
	private PlayerDAOImpl() {}
	public static PlayerDAOImpl getPlayerDAO() {
		if (instance == null) {
			instance = new PlayerDAOImpl();
		}
		return instance;
	}
	
	// DML
	public Player insertPlayer(Player player) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO PLAYER values(NULL,?,?,?,?,?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, player.getUsername());
				ps.setString(2, hashPassword(player.getUsername(), player.getPassword()));
				ps.setString(3, player.getEmail());
				ps.setString(4, player.getFirstName());
				ps.setString(5, player.getLastName());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getPlayer(player.getUsername());	// to return the triggered id
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Player updatePlayer(Player player) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE PLAYER SET "
					+ "P_USERNAME = ?, P_PASSWORD = ?, P_EMAIL = ?, "
					+ "P_FIRSTNAME = ?, P_LASTNAME = ? WHERE P_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, player.getUsername());
				ps.setString(2, hashPassword(player.getUsername(), player.getPassword()));
				ps.setString(3, player.getEmail());
				ps.setString(4, player.getFirstName());
				ps.setString(5, player.getLastName());
				ps.setInt(6, player.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getPlayer(player.getUsername());	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Player deletePlayer(Player player) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM PLAYER WHERE P_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, player.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return player;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	// DQL
	public Player getPlayer(int playerId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM PLAYER WHERE P_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, playerId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Player(
								rs.getInt("P_ID"),
								rs.getString("P_USERNAME"), 
								rs.getString("P_PASSWORD"),
								rs.getString("P_EMAIL"),
								rs.getString("P_FIRSTNAME"), 
								rs.getString("P_LASTNAME")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Player getPlayer(String username) {
		Player player = new Player();
		Connection connection = null;
		connection = cu.getConnection();
		String sql = "SELECT * FROM PLAYER WHERE P_USERNAME = ?";
		try  {
			PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						player = new Player(
								rs.getInt("P_ID"),
								rs.getString("P_USERNAME"), 
								rs.getString("P_PASSWORD"),
								rs.getString("P_EMAIL"),
								rs.getString("P_FIRSTNAME"), 
								rs.getString("P_LASTNAME")
								);
				}
					System.out.println(username);
					System.out.println(player);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return player;
	}
	public ArrayList<Player> getAllPlayers() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM PLAYER";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Player> players = new ArrayList<Player>();
					while (rs.next()) {
						players.add(new Player(
								rs.getInt("P_ID"),
								rs.getString("P_USERNAME"), 
								rs.getString("P_PASSWORD"),
								rs.getString("P_EMAIL"),
								rs.getString("P_FIRSTNAME"), 
								rs.getString("P_LASTNAME")
								));
					}
					return players;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	// DML
	public boolean insertCredentials(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "CREATE USER " + username + " IDENTIFIED BY " + password;
			try (Statement stmt = connection.createStatement()) {
				stmt.execute(sql);
				return true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	public boolean updateCredentials(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "ALTER USER " + username + " IDENTIFIED BY " + password;
			try (Statement stmt = connection.createStatement()) {
				stmt.executeUpdate(sql);
				return true;	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	public boolean deleteCredentials(String username) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DROP USER " + username;
			try (Statement stmt = connection.createStatement()) {
				stmt.executeUpdate(sql);
				return true;	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	// DCL
	public boolean grantDBPermissions(String username) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql;
			try (Statement stmt = connection.createStatement()) {
				sql = "GRANT CREATE SESSION TO " + username;
				stmt.execute(sql);
				sql = "GRANT SELECT, UPDATE ON PLAYER TO " + username;
				stmt.execute(sql);
				sql = "GRANT SELECT, INSERT, UPDATE, DELETE ON GAME_SESSION TO " + username;
				stmt.execute(sql);
				sql = "GRANT SELECT ON GAME_SESSION_SEQ TO " + username;
				stmt.execute(sql);
				return true;	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	public boolean revokeDBPermissions(String username) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql;
			try (Statement stmt = connection.createStatement()) {
				sql = "REVOKE CREATE SESSION FROM " + username;
				stmt.execute(sql);
				sql = "REVOKE SELECT, INSERT, UPDATE, DELETE ON GAME_SESSION FROM " + username;
				stmt.execute(sql);
				sql = "REVOKE SELECT ON GAME_SESSION_SEQ FROM " + username;
				stmt.execute(sql);
				return true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	public String hashPassword(String username, String password) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT GET_PLAYER_HASH(?, ?) FROM dual";
			try (CallableStatement cs = connection.prepareCall(sql)) {
				cs.setString(1, username);
				cs.setString(2, password);
				ResultSet rs = cs.executeQuery();
				if (rs.next()) {
					return rs.getString(1);	
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
	/*
	 *Kale: Adding attemptAuthentication() method implementation for logging in
	 */
	//*************************************************************************
	//*************************************************************************
	//*************************************************************************
	public Player attemptAuthentication(String username, String password) {
		Player player = null;
		Connection connection = null;
		connection = cu.getConnection();
		
		System.out.println("username: " + username);
		
			String sql = "Select * from USER WHERE U_USERNAME = ? AND U_PASSWORD = ?";
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,username);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					player = new Player(
						rs.getInt("U_ID"),
						rs.getString("U_USERNAME"), 
						rs.getString("U_PASSWORD"),
						rs.getString("U_EMAIL"),
						rs.getString("U_FIRSTNAME"), 
						rs.getString("U_LASTNAME")
						);
				}	
				System.out.println(player);
			} 
		
		catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return player;
	}
	//*************************************************************************
	//*************************************************************************
	//*************************************************************************
	
	
}
