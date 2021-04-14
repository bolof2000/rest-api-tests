package conduitapi.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

public class Listeners extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext testContext){
        htmlReporter = new ExtentHtmlReporter("/Volumes/DevelopmentEnv/End-to-end-API-Automation/End-to-end-API-Automation/reports/reports.html");
       // htmlReporter = new ExtentHtmlReporter("user.dir"+"/reports/report.html");

       htmlReporter.config().setDocumentTitle("API Automation Report");
       htmlReporter.config().setReportName("Post and Get reports ");
       htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("User","Victor");
        extent.setSystemInfo("Enviroment","QA");

    }
    public void onTestSuccess(ITestResult result){

        test = extent.createTest(result.getName());
        test.log(Status.PASS,"Test Case Passed IS " + result.getName());
    }

    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.FAIL,"Test CASE FAILED IS " + result.getName());
        test.log(Status.FAIL,"TEST CASE FAILED IS" + result.getThrowable());
    }
    @AfterMethod
    public void afterMethod() {
        extent.flush();
    }

}
