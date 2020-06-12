package com.rmgyantra.databaseLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

/**
 * 
 * @author adikiprakash
 *
 */
public class DatabaseUtility {

	static Connection con;
	static ResultSet result;

	/**
	 * connectToDB method will create a connection to database
	 */
	public static void connectToDB() {
		try {
			DriverManager.registerDriver(new Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * executeQuery method to get the data from database by executing query
	 * @param query
	 * @return ResultSet
	 */
	public static ResultSet executeQuery(String query) {
		try {
			result = con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String[] getResultSetData(ResultSet result, int...index) {
		int length = index.length;
		String [] str = new String[length];
		
		try {
			while(result.next()) {
				for (int i = 0; i < index.length; i++) {
					str[i] = result.getString(index[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}


	/**
	 * closeDB method will close the connection to the database
	 */
	public static void closeDB() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
