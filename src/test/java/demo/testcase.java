package demo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class testcase {
	  @Test
	    public void launch() throws MalformedURLException {
	 System.out.println("Starting RemoteWebDriver...");

     // Selenium Server URL
     URL seleniumServerURL = new URL("http://localhost:4444/wd/hub");

     // Chrome Options
     ChromeOptions options = new ChromeOptions();
     options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
     options.addArguments("--remote-allow-origins=*");

     // Initialize RemoteWebDriver
     WebDriver driver = new RemoteWebDriver(seleniumServerURL, options);

     // Navigate to a test page
     driver.get("https://www.google.com");
     System.out.println("Page title is: " + driver.getTitle());

     // Close the driver
     driver.quit();
 }
}
