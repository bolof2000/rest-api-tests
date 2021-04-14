package conduitapi.testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.*;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import org.testng.annotations.*;

public class TC001_User_SignUp_Test  {

    public static RequestSpecification httpRequest;
    public static Response response;
    @BeforeClass
    public static void signUpTestSetUp() throws IOException, InterruptedException {
       // RestAssured.baseURI = "https://conduit.productionready.io/api/";
        httpRequest = RestAssured.given();
        FileInputStream files = new FileInputStream(new File("signup.json"));
        System.out.println(files.toString());
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(IOUtils.toString(files,"UTF-8"));
        response = httpRequest.request(Method.POST,"users");
        Thread.sleep(5);
        System.out.println(response.statusCode());
    
}

    @Test
    public void validateresponseBody(){

        String responseBody = response.getBody().asString();
        JsonPath result = new JsonPath(responseBody);
        System.out.println(responseBody);
        Assert.assertTrue(responseBody != null);
    }

}
