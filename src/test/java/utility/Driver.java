package utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	static WebDriver driver;
	static WebDriverWait wait;
	static Properties prop;

	public static WebDriver setBrowserInstance(String browserName) {
		prop = PropertyReader.getInstance();
		int timeInSeconds = Integer.parseInt(prop.getProperty("WAIT_TIMEOUT_IN_SECS"));

		switch (browserName.toLowerCase().trim()) {
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "chrome":
		default:
			WebDriverManager.chromedriver().setup();
			ChromeOptions cp = new ChromeOptions();
			cp.addArguments("--disable-notifications");
			driver = new ChromeDriver(cp);
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
		Loggers.config("Implicit wait time for driver -> " + timeInSeconds + " seconds");

		wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		Loggers.config("Explicit wait time for driver -> " + timeInSeconds + " seconds");
		return driver;
	}

	public static String getSnapshot(String fileName) {
		String fullPath = "./screenshots/" + fileName + ".png";
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(fullPath));
			return fullPath;
		} catch (IOException e) {
			Loggers.error("Error with Screenshot");
			return null;
		}
	}

	public static void waitForElementToBeDisplayed(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForAllElementsToBeDisplayed(String xpathLocator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathLocator)));
	}

	public static void waitForElementToBePresent(String xpathLocator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
	}

	public static void waitForAllElementsToBePresent(String xpathLocator) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocator)));
	}

}
