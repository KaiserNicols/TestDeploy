package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	
	// Singleton setup
	private static UserDAOImpl instance;
	private UserDAOImpl() {}
	public static UserDAOImpl getUserDAO() {
		if (instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}
	
	// DML
	public User insertUser(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO IMDB_USER values(NULL,?,?,?,?,?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, user.getUsername());
				ps.setString(2, hashPassword(user.getUsername(), user.getPassword()));
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getFirstName());
				ps.setString(5, user.getLastName());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getUser(user.getUsername());	// to return the triggered id
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public User updateUser(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE IMDB_USER SET "
					+ "U_USERNAME = ?, U_PASSWORD = ?, U_EMAIL = ?, "
					+ "U_FIRSTNAME = ?, U_LASTNAME = ? WHERE U_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, user.getUsername());
				ps.setString(2, hashPassword(user.getUsername(), user.getPassword()));
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getFirstName());
				ps.setString(5, user.getLastName());
				ps.setInt(6, user.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getUser(user.getUsername());	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public User deleteUser(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM IMDB_USER WHERE U_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, user.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return user;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	// DQL
	public User getUser(int userId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM IMDB_USER WHERE U_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, userId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new User(
								rs.getInt("U_ID"),
								rs.getString("U_USERNAME"), 
								rs.getString("U_PASSWORD"),
								rs.getString("U_EMAIL"),
								rs.getString("U_FIRSTNAME"), 
								rs.getString("U_LASTNAME")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public User getUser(String username) {
		User user = new User();
		Connection connection = null;
		connection = cu.getConnection();
		String sql = "SELECT * FROM IMDB_USER WHERE U_USERNAME = ?";
		try  {
			PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						user = new User(
								rs.getInt("U_ID"),
								rs.getString("U_USERNAME"), 
								rs.getString("U_PASSWORD"),
								rs.getString("U_EMAIL"),
								rs.getString("U_FIRSTNAME"), 
								rs.getString("U_LASTNAME")
								);
				}
					System.out.println(username);
					System.out.println(user);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return user;
	}
	public ArrayList<User> getAllUsers() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM IMDB_USER";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<User> users = new ArrayList<User>();
					while (rs.next()) {
						users.add(new User(
								rs.getInt("U_ID"),
								rs.getString("U_USERNAME"), 
								rs.getString("U_PASSWORD"),
								rs.getString("U_EMAIL"),
								rs.getString("U_FIRSTNAME"), 
								rs.getString("U_LASTNAME")
								));
					}
					return users;
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
			String sql = "SELECT GET_USER_HASH(?, ?) FROM dual";
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
	public User attemptAuthentication(String username, String password) {
		User user = null;
		Connection connection = null;
		connection = cu.getConnection();
		
		System.out.println("username: " + username);
		
			String sql = "SELECT * FROM IMDB_USER WHERE U_USERNAME = ? AND U_PASSWORD = ?";
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,username);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					user = new User(
						rs.getInt("U_ID"),
						rs.getString("U_USERNAME"), 
						rs.getString("U_PASSWORD"),
						rs.getString("U_EMAIL"),
						rs.getString("U_FIRSTNAME"), 
						rs.getString("U_LASTNAME")
						);
				}	
				System.out.println(user);
				System.out.println("Here?");
				System.out.println(user);
			} 
		
		catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return user;
	}
	//*************************************************************************
	//*************************************************************************
	//*************************************************************************
	
	
}
