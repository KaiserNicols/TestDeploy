package com.revature.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.RecommendationDAO;
import com.revature.dao.RecommendationDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.model.Recommendation;
import com.revature.model.User;

public class RecommendationTests {

	private static Recommendation testRec = new Recommendation(-1, -1, -1, false, new Date().toString());
	private static RecommendationDAO rdao = RecommendationDAOImpl.getRecommendationDAO();
	
	private static User testUser = new User(-1, "JUnitTest", "JUnitTest", "JUnitTest@gmail.com", "firstName", "lastName");
	private static UserDAO udao = UserDAOImpl.getUserDAO();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//testUser = udao.insertUser(testUser);
		testRec = new Recommendation(-1, testUser.getId(), -1, false, new Date().toString());
		testRec = rdao.insertRecommendation(testRec);
		assertNotNull(testRec);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		testRec = rdao.deleteRecommendation(testRec);
		testUser = udao.deleteUser(testUser);
		assertNotNull(testRec);
	}
	
	@Test
	public void testUpdateRecommendation() {
		testRec.setHelpful(true);
		assertTrue(testRec.equals(rdao.updateRecommendation(testRec)));
	}
	@Test
	public void testGetRecommendationById() {
		System.out.println(testRec.toString());
		assertTrue(testRec.equals(rdao.getRecommendation(testRec.getId())));
	}
	@Test
	public void testGetRecommendationByUniques() {
		assertTrue(testRec.equals(rdao.getRecommendation(testRec.getUserId(), testRec.getMovieId(), testRec.getDate())));
	}
	@Test
	public void testGetAllRecommendationsByUserId() {
		assertNotNull(rdao.getAllRecommendations(testRec.getUserId()));
	}
	@Test
	public void testGetAllRecommendations() {
		assertNotNull(rdao.getAllRecommendations());
	}
}
