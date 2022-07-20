package utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting extends BaseClass implements ITestListener {
	private static ExtentSparkReporter sparkReport;
	private static ExtentReports extent;
	private static String newDate;

	public void onStart(ITestContext context) {
		Date date = new Date();
		newDate = date.toString().replaceAll(" ", "_").replaceAll(":", "_");

		sparkReport = new ExtentSparkReporter("./reports/Report_" + newDate + ".html");
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
		testLog = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		testLog.pass(MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		testLog.fail(MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

		String screenshotPath = DriverFactory.getSnapshot(result.getName() + "_" + newDate);
		System.out.println("Screenshot Path :: " + screenshotPath);
//		if (!screenshotPath.isEmpty())
		testLog.fail("Screenshot Taken : ",
				MediaEntityBuilder.createScreenCaptureFromPath("." + screenshotPath).build());
//		else
//			testLog.fail("Screenshot Taken : Error in taking screenshot");
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
