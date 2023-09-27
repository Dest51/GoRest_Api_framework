package api.endpoints;


import static io.restassured.RestAssured.given;
import api.payload.User; 
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import res.AppConfig;

//UserEndPoints.java
//Created to perform CRUD operations

public class UserEndPoints {

    static AppConfig appConfig = new AppConfig();
    static String bearerToken = appConfig.getBearerToken();
	
	public static Response createUser(User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.header("Authorization", "Bearer " + bearerToken)
			.body(payload)
		.when()
			.post(Routes.post_url);
			
		
		return response;
	}

	public static Response readUser(String id)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.header("Authorization", "Bearer " + bearerToken)
			.pathParam("id", id)
		.when()
			.get(Routes.get_url);
			
		
		return response;
	}
	
	public static Response readUser_custom_id(String id)
	{
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", "Bearer " + bearerToken)
			.get(Routes.get_url_custom + id);
			
		
		return response;
	}
	
	
	public static Response updateUser(String id, User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.header("Authorization", "Bearer " + bearerToken)
			.pathParam("id", id)
			.body(payload)
		.when()
			.put(Routes.update_url);
			
		
		return response;
	}
	
	public static Response deleteUser(String id)
	{
		Response response = given()
			.header("Authorization", "Bearer " + bearerToken)
			.pathParam("id", id)
		.when()
			.delete(Routes.delete_url);
			
		
		return response;
	}
	

}
