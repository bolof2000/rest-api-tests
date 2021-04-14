package conduitapi.testCases;

import com.google.gson.JsonObject;
import conduitapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TC001_Post_User_Login_Test extends TestBase {
    @BeforeClass
   public static void  createLogin() throws InterruptedException, IOException {
        FileInputStream files = new FileInputStream(new File("/Volumes/DevelopmentEnv/End-to-end-API-Automation/End-to-end-API-Automation/src/test/java/conduitapi/test-data/signup.json"));
        httpRequest.body(IOUtils.toString(files));
        response = httpRequest.request(Method.POST,"users");
        Thread.sleep(5);
    }

    @BeforeMethod
    public String getToken(){
       String token =  response.jsonPath().get("user.token");
       return token;
    }

    @Test
   public void checkResponseBody(){
    String responseBody = response.getBody().prettyPrint();
        JsonPath result = new JsonPath(responseBody);
    }

    @Test
   public void checkContentType(){
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType,"application/json; charset=utf-8");
    }
    @Test
    public void checkEncoding(){
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding,"gzip");
    }

    public void checkStatusLine(){
        String statusLine = response.statusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }
    @Test
  public void checkServerType(){
        String serverType = response.header("Server");
        Assert.assertEquals(serverType,"cloudflare");
    }

    @AfterMethod
    void tearDown(){
        System.out.println("********** Finished TC001-test");  // re-implement with logger
    }


}
