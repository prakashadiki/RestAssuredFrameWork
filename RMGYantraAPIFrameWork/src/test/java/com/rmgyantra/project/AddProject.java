package com.rmgyantra.project;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rmgyantra.databaseLib.DatabaseUtility;
import com.rmgyantra.genericLib.BaseLib;
import com.rmgyantra.genericLib.IEndPoints;
import com.rmgyantra.jsonUtilLib.JsonUtillty;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * 
 * @author adikiprakash
 *
 */
public class AddProject extends BaseLib {

	@Test
	public void addSingleProjectTest() {
		
		String actDBProjectName = null;
		String actDBProjectStatus = null;
		
		try {
			FileInputStream fis = new FileInputStream(new File("./resource/AddSingleProject.json"));
			Response response = given()
						.contentType(ContentType.JSON)
					.and()
						.body(IOUtils.toByteArray(fis)).
					when()
						.post(IEndPoints.add_Single_project);
			
			response.then().assertThat().statusCode(201);
			//long time = response.getTimeIn(TimeUnit.MILLISECONDS);
			String actProjectName = JsonUtillty.getJsonString(response, "projectName");
			ResultSet result = DatabaseUtility.executeQuery("select * from project where project_name = '"+ actProjectName+"';");
			
			/*while(result.next()) {
				actDBProjectName = result.getString(4);
				actDBProjectStatus = result.getString(5);
			}*/
			String[] data = DatabaseUtility.getResultSetData(result, 4, 5);
			
			actDBProjectName = data[0];
			actDBProjectStatus = data[1];
			Assert.assertEquals(actDBProjectStatus, "Created");
			Assert.assertEquals(actDBProjectName, actProjectName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
