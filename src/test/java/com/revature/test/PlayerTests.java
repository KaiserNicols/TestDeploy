package com.revature.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.PlayerDAO;
import com.revature.dao.PlayerDAOImpl;
import com.revature.model.Player;
import com.revature.util.ConnectionUtil;

public class PlayerTests {

	private static Player test = new Player(-1, "JUnitTest", "JUnitTest", "JUnitTest@gmail.com", "firstName", "lastName");
	private static PlayerDAO dao = PlayerDAOImpl.getPlayerDAO();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionUtil.defaultCredentials();
		dao.insertCredentials(test.getUsername(), test.getPassword());
		dao.grantDBPermissions(test.getUsername());
		test = dao.insertPlayer(test);
		assertNotNull(test);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		test = dao.deletePlayer(test);
		dao.revokeDBPermissions(test.getUsername());
		dao.deleteCredentials(test.getUsername());
		assertNotNull(test);
	}
	
	@Test
	public void testUpdatePlayer() {
		Player returned;
		test.setPassword("newPassword");
		test.setEmail("newEmail@gmail.com");
		test.setFirstName("newFirstName");
		test.setLastName("newLastName");
		// returned will contain hashed password
		returned = dao.updatePlayer(test);
		// update our test to have the same hashed password
		test.setPassword(dao.hashPassword(test.getUsername(), test.getPassword()));
		assertTrue(test.equals(returned));
	}
	@Test
	public void testGetPlayerById() {
		assertTrue(test.equals(dao.getPlayer(test.getId())));
	}
	@Test
	public void testGetPlayerByUsername() {
		assertTrue(test.equals(dao.getPlayer(test.getUsername())));
	}
	@Test
	public void testGetAllPlayers() {
		assertNotNull(dao.getAllPlayers());
	}
	@Test
	public void testUpdateCredentials() {
		assertTrue(dao.updateCredentials(test.getUsername(), "newCredentialPassword"));
	}
	@Test
	public void testHashPassword() {
		assertNotNull(dao.hashPassword(test.getUsername(), test.getPassword()));
	}
}
