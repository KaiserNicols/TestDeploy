package com.revature.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.GameDAO;
import com.revature.dao.GameDAOImpl;
import com.revature.dao.GameSessionDAO;
import com.revature.dao.GameSessionDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.model.Game;
import com.revature.model.GameSession;
import com.revature.model.GameSessionStatus;
import com.revature.model.GameType;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class GameSessionTests {

//	private static User playerTest = new User(-1, "JUnitTest", "JUnitTest", "JUnitTest@gmail.com", "firstName", "lastName");
//	private static Game gameTest = new Game(-1, "JUnitTest", GameType.OTHER);
//	private static GameSession sessionTest = new GameSession(-1, -1, -1, GameSessionStatus.PENDING, new Date().toString());
//	private static UserDAO playerdao = UserDAOImpl.getPlayerDAO();
//	private static GameDAO gamedao = GameDAOImpl.getGameDAO();
//	private static GameSessionDAO sessiondao = GameSessionDAOImpl.getGameSessionDAO();
//	/*
//	 * @BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		ConnectionUtil.defaultCredentials();
//		playerTest = playerdao.insertPlayer(playerTest);
//		gameTest = gamedao.insertGame(gameTest);
//		sessionTest.setPlayerId(playerTest.getId());
//		sessionTest.setGameId(gameTest.getId());
//		sessionTest = sessiondao.insertGameSession(sessionTest);
//		assertNotNull(sessionTest);
//	}
//	 */
//	
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		sessionTest = sessiondao.deleteGameSession(sessionTest);
//		gameTest = gamedao.deleteGame(gameTest);
//		playerTest = playerdao.deletePlayer(playerTest);
//		assertNotNull(sessionTest);
//	}
//	
//	@Test
//	public void testUpdateGameSession() {
//		sessionTest.setStatus(GameSessionStatus.LOSS);
//		assertTrue(sessionTest.equals(sessiondao.updateGameSession(sessionTest)));
//	}
//	@Test
//	public void testGetGameSessionBySessionId() {
//		assertTrue(sessionTest.equals(sessiondao.getGameSession(sessionTest.getId())));
//	}
//	@Test
//	public void testGetAllGameSessionsByPID() {
//		assertNotNull(sessiondao.getAllGameSessionsByPID(sessionTest.getPlayerId()));
//	}
//	@Test
//	public void testGetAllGameSessionsByGID() {
//		assertNotNull(sessiondao.getAllGameSessionsByGID(sessionTest.getGameId()));
//	}
//	@Test
//	public void testGetAllGameSessionsByStatus() {
//		assertNotNull(sessiondao.getAllGameSessions(sessionTest.getStatus()));
//	}
//	@Test
//	public void testGetAllGameSessions() {
//		assertNotNull(sessiondao.getAllGameSessions());
//	}

}
