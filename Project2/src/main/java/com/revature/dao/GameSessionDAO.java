package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.GameSession;
import com.revature.model.GameSessionStatus;

public interface GameSessionDAO {
	public GameSession insertGameSession(GameSession session);
	public GameSession updateGameSession(GameSession session);
	public GameSession deleteGameSession(GameSession session);	
	
	public GameSession getGameSession(int sessionId);
	public GameSession getGameSession(int playerId, int gameId, GameSessionStatus status, String date);
	public ArrayList<GameSession> getAllGameSessionsByPID(int playerId);
	public ArrayList<GameSession> getAllGameSessionsByGID(int gameId);
	public ArrayList<GameSession> getAllGameSessions(GameSessionStatus sessionStatus);
	public ArrayList<GameSession> getAllGameSessions();
}
