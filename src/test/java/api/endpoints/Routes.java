package api.endpoints;


/*
  Main URL: https://gorest.co.in
  
  Create user (POST): https://gorest.co.in/public/v2/users
  Get user (GET): https://gorest.co.in/public/v2/users/{id}
  Update user (PUT): https://gorest.co.in/public/v2/users/{id}
  Delete user (DELETE): https://gorest.co.in/public/v2/users/{id}
 
 */
public class Routes {
	
	public static String base_url = "https://gorest.co.in/public/v2";
	
	//user module
	public static String post_url = base_url + "/users";
	public static String get_url = base_url + "/users/{id}";
	public static String update_url = base_url + "/users/{id}";
	public static String delete_url = base_url + "/users/{id}";
	
	public static String get_url_custom = base_url + "/users/";


	
	
}
