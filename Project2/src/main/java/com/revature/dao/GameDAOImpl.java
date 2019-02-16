package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.model.Game;
import com.revature.model.GameType;
import com.revature.util.ConnectionUtil;

public class GameDAOImpl implements GameDAO {
	
	private static Logger logger = Logger.getLogger(GameDAOImpl.class);
	
	// Singleton setup
	private static GameDAOImpl instance;
	private GameDAOImpl() {}
	public static GameDAOImpl getGameDAO() {
		if (instance == null) {
			instance = new GameDAOImpl();
		}
		return instance;
	}
	
	// DML
	public Game insertGame(Game game) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO GAME values(NULL,?,?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, game.getName());
				ps.setString(2, game.getType().toString());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getGame(game.getName());	// to return the triggered id
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Game updateGame(Game game) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE GAME SET G_NAME = ?, G_TYPE = ? WHERE G_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, game.getName());
				ps.setString(2, game.getType().toString());
				ps.setInt(3, game.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getGame(game.getName());	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Game deleteGame(Game game) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM GAME WHERE G_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, game.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return game;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	// DQL
	public Game getGame(int gameId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME WHERE G_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, gameId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Game(
								rs.getInt("G_ID"),
								rs.getString("G_NAME"), 
								GameType.valueOf(rs.getString("G_TYPE"))
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Game getGame(String gameName) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME WHERE G_NAME = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, gameName);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Game(
								rs.getInt("G_ID"),
								rs.getString("G_NAME"), 
								GameType.valueOf(rs.getString("G_TYPE"))
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public ArrayList<Game> getAllGames() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Game> games = new ArrayList<Game>();
					while (rs.next()) {
						games.add(new Game(
								rs.getInt("G_ID"),
								rs.getString("G_NAME"), 
								GameType.valueOf(rs.getString("G_TYPE"))
								));
					}
					return games;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
