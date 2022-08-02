package utility;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting extends BaseClass implements ITestListener {
	private static ExtentSparkReporter sparkReport;
	private static ExtentReports extent;
	private static String newDate;
	private static String testName;
	private static String reportPath;

	public void onStart(ITestContext context) {
		Date date = new Date();
		newDate = date.toString().replaceAll(" ", "_").replaceAll(":", "_");
		reportPath = "./reports/Report_" + newDate + ".html";
		sparkReport = new ExtentSparkReporter(reportPath);
		try {
			final File sparkConfig = new File(".//src//test//resources//configuration//spark-config.xml");
			sparkReport.loadXMLConfig(sparkConfig);
		} catch (IOException e) {
			System.out.println("Error in reading SparkReporter Config File");
		}

		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Nikhil");
	}

	public void onTestStart(ITestResult result) {
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
		Test test = method.getAnnotation(Test.class);
		testName = (test.testName() == null) ? result.getName() : test.testName();

		Loggers.nextLine();
		Loggers.info(testName + " test started.");

		testLog = extent.createTest(testName);
	}

	public void onTestSuccess(ITestResult result) {
		Loggers.pass(testName + " test succesfully passed.");

		String logText = "<b>Test Method :: " + result.getName() + " Successfully Passed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testLog.pass(m);
	}

	public void onTestFailure(ITestResult result) {
		Loggers.error(testName + " test failed.");
		String logText = "<b>Test Method :: " + result.getName() + " Failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		testLog.fail(m);

		String screenshotPath = DriverFactory.getSnapshot(result.getName() + "_" + newDate);
		Loggers.error("Screenshot Path -> " + screenshotPath);
		testLog.fail("Screenshot Taken : ",
				MediaEntityBuilder.createScreenCaptureFromPath("." + screenshotPath).build());
	}

	public void onTestSkipped(ITestResult result) {
		Loggers.skip(testName + " test failed.");
		String logText = "<b>Test Method :: " + result.getName() + " Skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testLog.skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		Loggers.info("Check detailed logs in html reporter located at - " + reportPath);
		Loggers.breakLine();
	}

}
