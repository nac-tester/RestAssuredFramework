package api.endpoints;

/*
Swagger URL-->https://petstore.swagger.io
Create user(post) : https://petstore.swagger.io/v2/user
Get user(get) : https://petstore.swagger.io/v2/user/{username}
Update user(put) : https://petstore.swagger.io/v2/user/{username}
Delete user : https://petstore.swagger.io/v2/user/{username}
*/

//contains URL's
public class Routes {              
 
	public static String base_url= "https://petstore.swagger.io/v2";
	
	//User module
	public static String post_url=base_url+"/user";                  //concatenate base_url with others end points
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//Store module
	//here u will create store module URL's.
	
	//Pet module
	//here u will create pet module URL's.



}
