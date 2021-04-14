package conduitapi.testCases;

import conduitapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.*;


public class TC001_Get_All_Tags extends TestBase  {

    /*
        The get http method interface executes before actual test scenario starts
     */
   //public static Logger logger;
    @BeforeClass
    public static void getAllTags() throws InterruptedException {
        System.out.println("Started TC001 execution");
        RestAssured.baseURI = "https://conduit.productionready.io/api/";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/tags");
        Thread.sleep(3);
    }

    /*
    Actual test cases implementations .
    The following validations is defined
    Test cases:
    Validate response Body
    Validate status code
    validate the response time
    validate the status line

     */

    @Test
    public void validateResponseBody(){
      System.out.println("**************  Checking response Body   ************");
        String responseBody = response.getBody().asString();
  System.out.println("Response Body===>"+ responseBody);
  System.out.println(response.prettyPrint());
  System.out.println(response.jsonPath().getString("SIDA"));
        Assert.assertTrue(responseBody != null);
    }

    @Test
    public void validateStatusCode(){
      System.out.println("**************  Checking status code  ************");
        int statusCode = response.getStatusCode();
    System.out.println("Status Code is ====> " + statusCode);
        Assert.assertEquals(statusCode,200);
    }


    @Test
    public void checkStatusLine(){
      String statusLine = response.getStatusLine();
      Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

}

