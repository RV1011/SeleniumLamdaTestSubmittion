package com.lamdatest.www;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumTest {

	public RemoteWebDriver driver = null;
	public String url = "https://www.lambdatest.com/";
	public static final String username = "ravisrm.raj10";
	public static final String auth_key = "RL3HhZXOtxibLRJggt0uFT6SPKsd9bRIbhMl5AAMaPgHisDDiO";
	public static final String URL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	@Test
	public void testScenario1() {
		try {

			driver.manage().window().maximize();
			driver.get("https://www.lambdatest.com/selenium-playground");
			driver.findElement(By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/simple-form-demo']"))
					.click();
			String url = driver.getCurrentUrl();
			Assert.assertTrue(url.contains("simple-form-demo"));
			String expectedMessage = "Welcome to LambdaTest.";
			driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(expectedMessage);
			driver.findElement(By.xpath("//button[@id='showInput']")).click();
			String actualMessage = driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertEquals(expectedMessage, actualMessage);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		

	}

	@Test
	public void testScenario2() {
		try {

			// 1. Open the https://www.lambdatest.com/selenium-playground page and
			// click “Drag & Drop Sliders” under “Progress Bars & Sliders”.
			driver.manage().window().maximize();
			driver.get("https://www.lambdatest.com/selenium-playground");
			driver.findElement(By
					.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo']"))
					.click();

			// 2. Select the slider “Default value 15” and drag the bar to make it 95 by
			// validating whether the range value shows 95.
			WebElement sliderStart = driver.findElement(By.xpath("//input[@value='15']"));
			 
			Actions action = new Actions(driver);
			action.dragAndDropBy(sliderStart, 120, 0).perform();
			String actualValue = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
			Assert.assertEquals("95", actualValue);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		

	}
	@Test
	public void testScenario3() {
		try {

			driver.manage().window().maximize();
			driver.get("https://www.lambdatest.com/selenium-playground");
			driver.findElement(By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/input-form-demo']")).click();
			//driver.findElement(By.xpath("//button[@class='btn btn-dark selenium_btn bg-black text-white hover:bg-lambda-900 py-5 px-10 rounded']")).click();
			//Thread.sleep(4);
			//String actualAleartMessage =driver.switchTo().alert().getText();
			//Assert.assertEquals("Please fill in the fields", actualAleartMessage);
			//Please fill out this field.
			driver.findElement(By.xpath("//input[@id='name']")).sendKeys("ravi");
			driver.findElement(By.xpath("//input[@id='inputEmail4']")).sendKeys("ravisrm.raj10@gmail.com");
			driver.findElement(By.xpath("//input[@id='inputPassword4']")).sendKeys("abcd$1234");
			driver.findElement(By.xpath("//input[@id='company']")).sendKeys("LamdaTest");
			driver.findElement(By.xpath("//input[@id='websitename']")).sendKeys("https://www.lambdatest.com");			
			WebElement country =driver.findElement(By.xpath("//select[@name='country']"));
			Select drpDown = new Select(country);
				drpDown.selectByVisibleText("United States");	
			driver.findElement(By.xpath("//input[@id='inputCity']")).sendKeys("unitedstate");
			driver.findElement(By.xpath("//input[@id='inputAddress1']")).sendKeys("123ABC");
			driver.findElement(By.xpath("//input[@id='inputAddress2']")).sendKeys("456DEF");
			driver.findElement(By.xpath("//input[@id='inputState']")).sendKeys("Bihar");
			
			driver.findElement(By.xpath("//input[@id='inputZip']")).sendKeys("12345");
			driver.findElement(By.xpath("//button[@class='btn btn-dark selenium_btn bg-black text-white hover:bg-lambda-900 py-5 px-10 rounded']")).click();
			String actualSuccessMessage =driver.findElement(By.xpath("//p[@class='success-msg hidden']")).getText();
			Assert.assertEquals("Thanks for contacting us, we will get back to you shortly.", actualSuccessMessage);
			
			
			
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		

	}
	
	/*
	 * public static void selDropDown(WebElement webele, String Cntry) {
	 * 
	 * Select drpdown = new Select(webele); List<WebElement> options =
	 * drpdown.getOptions(); for (WebElement option : options) { if
	 * (option.getText().equals(Cntry)) { option.click(); break;
	 * 
	 * } } }
	 */

	@BeforeClass
	@Parameters(value={"browser","version","platform"})
	public void setUp(String browser, String version, String platform) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform); //															
		capabilities.setCapability("build", "TestNG_login_3");
		capabilities.setCapability("name", "TestNG_login_3");
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		try {

			driver = new RemoteWebDriver(new URL("https://" + username + ":" + auth_key + URL), capabilities);
			driver.setFileDetector(new LocalFileDetector());

		} catch (Exception e) {

			System.out.println("Invalid grid URL" + e.getMessage());
		}

	}

	private void tearDown() {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status); // Lambda status will be reflected
																					// as either passed/ failed

			driver.quit();

			System.out.println("The setup process is completed");

		}
	}

}
