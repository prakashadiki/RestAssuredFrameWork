package com.rmgyantra.genericLib;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.rmgyantra.databaseLib.DatabaseUtility;

import static io.restassured.RestAssured.*;

/**
 * 
 * @author adikiprakash
 *
 */
public class BaseLib {

	
	/**
	 * configBS method will be used invoke connectToDB method from DatabaseUtility
	 * To initilize baseURI, port number authentication credentials.
	 */
	@BeforeSuite
	public void configBS() {
		DatabaseUtility.connectToDB();
		baseURI = "http://adikiprakash";
		port = 8084;
	}
	
	
	/**
	 * configAS method is used to invoke the closeDB method from DatabaseUtility.
	 */
	@AfterSuite
	public void configAS() {
		DatabaseUtility.closeDB();
	}
}
