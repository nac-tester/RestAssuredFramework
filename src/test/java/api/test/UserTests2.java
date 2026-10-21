
package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserTests2 {
	
	Faker faker;           //able to create fake data
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup()              //----------------1st setup the data using faker
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());      //
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("***************Creating User*************");
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************User is Created*************");
	}
  
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("***************Reading User Info*************");
		Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************User Info is Displayed*************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("***************Updating User*************");
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************User Updated*************");
		
		//Checking data after updation
		Response responseAfterupdate=UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
	}
	
	
	@Test(priority=4)
	public void testDeleteByName()
	{
		logger.info("***************Deleting User*************");
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************User Deleted *************");
	}
	}
	

