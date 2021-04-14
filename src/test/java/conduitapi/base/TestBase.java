package conduitapi.base;

import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
 public static RequestSpecification httpRequest;
 public static Response response;

 @BeforeClass
 public static void setup() throws IOException {
  RestAssured.baseURI = RestAssured.baseURI = "https://conduit.productionready.io/api/";
  httpRequest = RestAssured.given();
  httpRequest.header("Content-Type","application/json");
 }
}
