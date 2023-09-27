package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	private JsonPath initialResponse;
    private String userId; 


	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setName(faker.name().fullName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setGender(faker.demographic().sex());
		userPayload.setStatus("active");
				
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
	    
	    String responseBody = response.body().asString();
	    JsonPath jsonPath = new JsonPath(responseBody);
	    userId = jsonPath.getString("id");
	    
	    initialResponse = jsonPath;
		
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(priority = 2)
	public void testGetUserById()
	{
		Response response = UserEndPoints.readUser(userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUserById()
	{
		//update data
		userPayload.setName(faker.name().fullName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.updateUser(userId, userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//check if data after update is not equals to the initial data
		Response responseAfterUpdate = UserEndPoints.readUser(userId);
	    String responseBody = responseAfterUpdate.body().asString();
	    JsonPath jsonPath = new JsonPath(responseBody);
	    
	    String userNewName = jsonPath.getString("name");
	    String userNewEmail = jsonPath.getString("email");

		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

		Assert.assertNotEquals(initialResponse.getString("name"), userNewName);
		Assert.assertNotEquals(initialResponse.getString("email"), userNewEmail);
		
		//check if ID after update equals to the initial data
		Assert.assertEquals(initialResponse.getString("id"), userId);

	}
	
	@Test(priority = 4)
	public void testDeleteUserById()
	{
		//delete user
		Response response = UserEndPoints.deleteUser(userId);
		response.then().log().all();
	    		
		Assert.assertEquals(response.getStatusCode(), 204);
		
		//check if user deleted successfully and can not be found in the system
		Response responseAfterDeleting = UserEndPoints.readUser(userId);
		response.then().log().all();
		Assert.assertEquals(responseAfterDeleting.getStatusCode(), 404);
		
	}

}
