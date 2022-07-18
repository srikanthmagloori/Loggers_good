package utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	protected static ExtentSparkReporter sparkReport;
	protected static ExtentReports extent;
	protected static ExtentTest testLog;

	private void reportSetup() throws IOException {
		Date date = new Date();
		String newDate = date.toString().replaceAll(" ", "_").replaceAll(":", "_");

		sparkReport = new ExtentSparkReporter("./reports/Report_" + newDate + ".html");
		final File sparkConfig = new File(".//src//test//resources//configuration//spark-config.xml");
		sparkReport.loadXMLConfig(sparkConfig);

		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Nikhil");
	}

	@BeforeClass
	@Parameters("browser")
	public void setup(@Optional String browser) throws IOException {
		reportSetup();
		prop = PropertyReader.getInstance();

		browser = (browser == null) ? prop.getProperty("BROWSER") : browser;
		driver = DriverFactory.setBrowserInstance(browser);
		System.out.println("===============================================\n");
		System.out.println("Driver Started in " + browser + " browser");

		String url = prop.getProperty("URL");
		System.out.println("Opening url :: " + url);
		driver.get(url);
	}

	@AfterClass
	public void tearDown() {
		System.out.println("Testing Completed");
		System.out.println("Closing the browser instance");
		System.out.println("\n===============================================");
		driver.quit();
		extent.flush();
	}
}
