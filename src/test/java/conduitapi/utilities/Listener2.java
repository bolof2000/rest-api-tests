package conduitapi.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener2 implements ITestListener {
    ExtentHtmlReporter extentHtmlReporter;
    ExtentReports extent;
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        extentHtmlReporter = new ExtentHtmlReporter("\"/Volumes/DevelopmentEnv/End-to-end-API-Automation/End-to-end-API-Automation/reports/report2.html");
        extentHtmlReporter.config().setTheme(Theme.DARK);
        extentHtmlReporter.config().setDocumentTitle("Automation Test report");
        extentHtmlReporter.config().setReportName("API Test reports");
        extent = new ExtentReports();
        extent.attachReporter(extentHtmlReporter);
        extent.setSystemInfo("User","Victor");
        extent.setSystemInfo("Enviroment","QA");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.PASS,"Test Case Passed IS " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL,"Test CASE FAILED IS " + result.getName());
        test.log(Status.FAIL,"TEST CASE FAILED IS" + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP,"Test Case Skipped IS" + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
