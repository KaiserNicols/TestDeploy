/*
 * package com.revature.util;

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

 */

package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private static final Logger logger = LogManager.getLogger(ConnectionUtil.class);
	private static ConnectionUtil cu = null;
	private static Properties prop = new Properties();
	private ConnectionUtil() {
		super();
		InputStream dbProps = ConnectionUtil.class.getClassLoader()
				.getResourceAsStream("database.properties");
		try {
			prop.load(dbProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ConnectionUtil getInstance() {
		if(cu==null)
			cu=new ConnectionUtil();
		return cu;
	}
	public static Connection getConnection() {
		Connection connection = null;
		try {
			// We have to register the driver class
			Class.forName(prop.getProperty("driver"));
			connection = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch (SQLException e) {
			logger.fatal("Failed to obtain JDBC Connection: {}");
			logger.fatal("SQL State: {}");
			logger.fatal("Error Code: {}");
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			logger.fatal("Failed to load JDBC Driver: {}");
			throw new RuntimeException(e);
		}
		return connection;
	}
}


