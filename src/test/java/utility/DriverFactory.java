package utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

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

public class DriverFactory {
	static WebDriver driver;
	static WebDriverWait wait;

	public static WebDriver setBrowserInstance(String browserName) {
		switch (browserName.toLowerCase()) {

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Loggers.config("Implicit wait time for driver -> 10 seconds");

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Loggers.config("Explicit wait time for driver -> 5 seconds");
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

	public static void waitForAllElementsToBeDisplayed(By locator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

}
