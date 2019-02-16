package com.revature.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.GameDAO;
import com.revature.dao.GameDAOImpl;
import com.revature.model.Game;
import com.revature.model.GameType;
import com.revature.util.ConnectionUtil;

public class GameTests {

	private static Game test = new Game(-1, "JUnitTest", GameType.OTHER);
	private static GameDAO dao = GameDAOImpl.getGameDAO();
	/*
	 * @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionUtil.defaultCredentials();
		test = dao.insertGame(test);
		assertNotNull(test);
	}
	 */
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		test = dao.deleteGame(test);
		assertNotNull(test);
	}
	
	@Test
	public void testUpdateGame() {
		test.setType(GameType.TWENTY_QUESTIONS);
		assertTrue(test.equals(dao.updateGame(test)));
	}
	@Test
	public void testGetGameById() {
		assertTrue(test.equals(dao.getGame(test.getId())));
	}
	@Test
	public void testGetGameByName() {
		assertTrue(test.equals(dao.getGame(test.getName())));
	}
	@Test
	public void testGetAllGames() {
		assertNotNull(dao.getAllGames());
	}
}
