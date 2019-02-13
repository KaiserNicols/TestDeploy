package com.revature.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	private static BufferedReader br;
	private static String url = "";
	private static String username = "";
	private static String password = "";
	private static String dbPropertiesPath;
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbPropertiesPath = Paths.get(".").toAbsolutePath() + "\\src\\main\\resources\\database.properties";
			defaultCredentials();
		} catch (ClassNotFoundException e) {
			logger.error("Loading driver exception due to: " + e.getMessage());
		}
	}
	
	public static Connection getConnection() throws SQLException {
		//logger.info("getConnection() -\nurl: " + url + "\nusername: " + username + "\npassword: " + password);
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void setCredentials(String username, String password) {
		//logger.info("New Credentials - username: " + username + " - password: " + password);
		ConnectionUtil.username = username;
		ConnectionUtil.password = password;
	}
	
	public static void defaultCredentials() {
		try {
			br = new BufferedReader(new FileReader(dbPropertiesPath));
			url = br.readLine();
			username = br.readLine();
			password = br.readLine();
			br.close();
		} catch (IOException e) {
			logger.error("defaultCredentials() exception due to: " + e.getMessage());
		}
	}
}
