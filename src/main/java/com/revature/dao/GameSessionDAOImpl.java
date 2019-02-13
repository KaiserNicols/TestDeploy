package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.model.GameSession;
import com.revature.model.GameSessionStatus;
import com.revature.util.ConnectionUtil;

public class GameSessionDAOImpl implements GameSessionDAO {
	
	private static Logger logger = Logger.getLogger(GameSessionDAOImpl.class);
	
	// Singleton setup
	private static GameSessionDAOImpl instance;
	private GameSessionDAOImpl() {}
	public static GameSessionDAOImpl getGameSessionDAO() {
		if (instance == null) {
			instance = new GameSessionDAOImpl();
		}
		return instance;
	}
	
	// DML
	public GameSession insertGameSession(GameSession session) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO GAME_SESSION values(NULL,?,?,?,?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, session.getPlayerId());
				ps.setInt(2, session.getGameId());
				ps.setString(3, session.getStatus().toString());
				ps.setString(4, session.getDate());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getGameSession(session.getPlayerId(), session.getGameId(), session.getStatus(), session.getDate());	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public GameSession updateGameSession(GameSession session) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE GAME_SESSION SET "
					+ "P_ID = ?, G_ID = ?, GS_STATUS = ?, "
					+ "GS_DATE = ? WHERE GS_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, session.getPlayerId());
				ps.setInt(2, session.getGameId());
				ps.setString(3, session.getStatus().toString());
				ps.setString(4, session.getDate());
				ps.setInt(5, session.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return getGameSession(session.getPlayerId(), session.getGameId(), session.getStatus(), session.getDate());	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public GameSession deleteGameSession(GameSession session) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM GAME_SESSION WHERE GS_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, session.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return session;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	//DQL
	public GameSession getGameSession(int sessionId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME_SESSION WHERE GS_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, sessionId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new GameSession(
								rs.getInt("GS_ID"),
								rs.getInt("P_ID"),
								rs.getInt("G_ID"),
								GameSessionStatus.valueOf(rs.getString("GS_STATUS")),
								rs.getString("GS_DATE")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public GameSession getGameSession(int playerId, int gameId, GameSessionStatus status, String date) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME_SESSION WHERE P_ID = ? AND G_ID = ? AND GS_STATUS = ? AND GS_Date = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, playerId);
				ps.setInt(2, gameId);
				ps.setString(3, status.toString());
				ps.setString(4, date);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new GameSession(
								rs.getInt("GS_ID"),
								rs.getInt("P_ID"),
								rs.getInt("G_ID"),
								GameSessionStatus.valueOf(rs.getString("GS_STATUS")),
								rs.getString("GS_DATE")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public ArrayList<GameSession> getAllGameSessionsByPID(int playerId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME_SESSION WHERE P_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, playerId);
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<GameSession> sessions = new ArrayList<GameSession>();
					while (rs.next()) {
						sessions.add(new GameSession(
								rs.getInt("GS_ID"),
								rs.getInt("P_ID"),
								rs.getInt("G_ID"),
								GameSessionStatus.valueOf(rs.getString("GS_STATUS")),
								rs.getString("GS_DATE")
								));
					}
					return sessions;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public ArrayList<GameSession> getAllGameSessionsByGID(int gameId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME_SESSION WHERE G_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, gameId);
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<GameSession> sessions = new ArrayList<GameSession>();
					while (rs.next()) {
						sessions.add(new GameSession(
								rs.getInt("GS_ID"),
								rs.getInt("P_ID"),
								rs.getInt("G_ID"),
								GameSessionStatus.valueOf(rs.getString("GS_STATUS")),
								rs.getString("GS_DATE")
								));
					}
					return sessions;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public ArrayList<GameSession> getAllGameSessions(GameSessionStatus sessionStatus) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME_SESSION WHERE GS_STATUS = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, sessionStatus.toString());
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<GameSession> sessions = new ArrayList<GameSession>();
					while (rs.next()) {
						sessions.add(new GameSession(
								rs.getInt("GS_ID"),
								rs.getInt("P_ID"),
								rs.getInt("G_ID"),
								GameSessionStatus.valueOf(rs.getString("GS_STATUS")),
								rs.getString("GS_DATE")
								));
					}
					return sessions;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public ArrayList<GameSession> getAllGameSessions() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GAME_SESSION";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<GameSession> sessions = new ArrayList<GameSession>();
					while (rs.next()) {
						sessions.add(new GameSession(
								rs.getInt("GS_ID"),
								rs.getInt("P_ID"),
								rs.getInt("G_ID"),
								GameSessionStatus.valueOf(rs.getString("GS_STATUS")),
								rs.getString("GS_DATE")
								));
					}
					return sessions;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
