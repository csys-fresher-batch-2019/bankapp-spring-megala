package bank.util;

import java.sql.Connection;
import java.sql.DriverManager;

import logger.Logger;

public class ConnectionUtil {
	private static final  Logger LOGGER = Logger.getInstance();
	private ConnectionUtil() {
		throw new IllegalStateException("Utility class");
	}
public static Connection getconnection() throws Exception{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		String server="13.235.147.120";
		String userName="megala";
		String password="megala";
		return DriverManager.getConnection("jdbc:oracle:thin:@"+server+":1521:XE", userName, password);
		
}}
