package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Recommendation;

public interface RecommendationDAO {
	// DML
	public Recommendation insertRecommendation(Recommendation recommendation);
	public Recommendation updateRecommendation(Recommendation recommendation);
	public Recommendation deleteRecommendation(Recommendation recommendation);
	
	// DQL
	public Recommendation getRecommendation(int recommendationId);
	public Recommendation getRecommendation(int userId, int movieId, String date);
	public ArrayList<Recommendation> getAllRecommendations(int userId);
	public ArrayList<Recommendation> getAllRecommendations();
}
