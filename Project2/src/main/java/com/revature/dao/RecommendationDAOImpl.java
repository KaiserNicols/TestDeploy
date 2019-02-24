package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.model.Recommendation;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class RecommendationDAOImpl implements RecommendationDAO {
	private static Logger logger = Logger.getLogger(RecommendationDAOImpl.class);
	
	// Singleton setup
	private static RecommendationDAOImpl instance;
	private RecommendationDAOImpl() {}
	public static RecommendationDAOImpl getRecommendationDAO() {
		if (instance == null) {
			instance = new RecommendationDAOImpl();
		}
		return instance;
	}
	
	// DML
	public Recommendation insertRecommendation(Recommendation recommendation) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String 
			sql = "INSERT INTO IMDB_RECOMMENDATION values(NULL,?,?,?,?)";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, recommendation.getUserId());
				ps.setInt(2, recommendation.getMovieId());
				ps.setString(3, recommendation.isHelpful() ? "1" : "0");
				ps.setString(4, recommendation.getDate());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				System.out.println(getRecommendation(recommendation.getUserId(), recommendation.getMovieId(), recommendation.getDate()));
				return getRecommendation(recommendation.getUserId(), recommendation.getMovieId(), recommendation.getDate());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Recommendation updateRecommendation(Recommendation recommendation) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE IMDB_RECOMMENDATION SET "
					+ "U_ID = ?, M_ID = ?, R_HELPFUL = ?, "
					+ "R_DATE = ? WHERE R_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, recommendation.getUserId());
				ps.setInt(2, recommendation.getMovieId());
				ps.setString(3, recommendation.isHelpful() ? "1" : "0");
				ps.setString(4, recommendation.getDate());
				ps.setInt(5, recommendation.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return recommendation;	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Recommendation deleteRecommendation(Recommendation recommendation) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM IMDB_RECOMMENDATION WHERE R_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, recommendation.getId());
				if (ps.executeUpdate() != 1) {
					throw new SQLException();
				}
				return recommendation;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	// DQL
	public Recommendation getRecommendation(int recommendationId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM IMDB_RECOMMENDATION WHERE R_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, recommendationId);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Recommendation(
								rs.getInt("R_ID"),
								rs.getInt("U_ID"),
								rs.getInt("M_ID"),
								rs.getBoolean("R_HELPFUL"),
								rs.getString("R_DATE")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public Recommendation getRecommendation(int userId, int movieId, String date) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM IMDB_RECOMMENDATION WHERE U_ID = ? AND M_ID = ? AND R_DATE = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, userId);
				ps.setInt(2, movieId);
				ps.setString(3, date);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						return new Recommendation(
								rs.getInt("R_ID"),
								rs.getInt("U_ID"),
								rs.getInt("M_ID"),
								rs.getBoolean("R_HELPFUL"),
								rs.getString("R_DATE")
								);
					}	
				}	
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public ArrayList<Recommendation> getAllRecommendations(int userId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM IMDB_RECOMMENDATION WHERE U_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, userId);
				try (ResultSet rs = ps.executeQuery()) {
					ArrayList<Recommendation> recommendation = new ArrayList<Recommendation>();
					while (rs.next()) {
						recommendation.add(new Recommendation(
								rs.getInt("R_ID"),
								rs.getInt("U_ID"),
								rs.getInt("M_ID"),
								rs.getBoolean("R_HELPFUL"),
								rs.getString("R_DATE")
								));
					}
					return recommendation;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	public ArrayList<Recommendation> getAllRecommendations() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM IMDB_RECOMMENDATION";
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					System.out.println("Made it here");
					ArrayList<Recommendation> recommendation = new ArrayList<Recommendation>();
					while (rs.next()) {
						recommendation.add(new Recommendation(
								rs.getInt("R_ID"),
								rs.getInt("U_ID"),
								rs.getInt("M_ID"),
								rs.getBoolean("R_HELPFUL"),
								rs.getString("R_DATE")
								));
					}
					return recommendation;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
