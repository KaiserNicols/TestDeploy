package com.revature.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserTests {

	private static User test = new User(-1, "JUnitTest", "JUnitTest", "JUnitTest@gmail.com", "firstName", "lastName");
	private static UserDAO dao = UserDAOImpl.getUserDAO();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//ConnectionUtil.defaultCredentials();
		//dao.insertCredentials(test.getUsername(), test.getPassword());
		//dao.grantDBPermissions(test.getUsername());
		test = dao.insertUser(test);
		assertNotNull(test);
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		test = dao.deleteUser(test);
		//dao.revokeDBPermissions(test.getUsername());
		//dao.deleteCredentials(test.getUsername());
		assertNotNull(test);
	}
	
	@Test
	public void testUpdatePlayer() {
		User returned;
		test.setPassword("newPassword");
		test.setEmail("newEmail@gmail.com");
		test.setFirstName("newFirstName");
		test.setLastName("newLastName");
		// returned will contain hashed password
		returned = dao.updateUser(test);
		// update our test to have the same hashed password
		test.setPassword(dao.hashPassword(test.getUsername(), test.getPassword()));
		assertTrue(test.equals(returned));
	}
	/*
	 * @Test
	public void testGetPlayerById() {
		assertTrue(test.equals(dao.getUser(test.getId())));
	}
	 */
	
	@Test
	public void testGetPlayerByUsername() {
		assertTrue(test.equals(dao.getUser(test.getUsername())));
	}
	@Test
	public void testGetAllPlayers() {
		assertNotNull(dao.getAllUsers());
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
