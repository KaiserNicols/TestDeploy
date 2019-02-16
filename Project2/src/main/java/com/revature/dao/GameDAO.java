package com.revature.dao;

import java.util.ArrayList;
import com.revature.model.Game;

public interface GameDAO {
	public Game insertGame(Game game);
	public Game updateGame(Game game);
	public Game deleteGame(Game game);	
	
	public Game getGame(int gameId);
	public Game getGame(String gameName);
	
	public ArrayList<Game> getAllGames();
}
