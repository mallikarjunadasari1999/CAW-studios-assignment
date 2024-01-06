package commonMethods;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utils;

public class BaseTest {

	public static JSONArray data;
	HashMap<String, String> appData;
	String filePath = "src/main/resources/data.json";
	String appDataFilePath = "src/main/resources/data.properties";
	public static WebDriver driver;

	@BeforeSuite
	public void configDataAndEnv() throws Exception {
		data = Utils.readDataFromJson(filePath, "data");
		appData = Utils.readDataFromPropertyFile(appDataFilePath);
	}

	@Parameters({ "Browser" })
	@BeforeClass
	public void launchBrowser(@Optional String browserName) {
		browserName = (browserName == null) ? "chrome" : browserName;
		switch (browserName.toLowerCase()) {
		case "chrome":
//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		}
	}

	@BeforeMethod
	public void launchApplication() {
		driver.manage().window().maximize();
		driver.get(appData.get("url"));
	}

	@AfterMethod
	public void closingBrowser() {
		driver.close();
		driver.quit();
	}

}