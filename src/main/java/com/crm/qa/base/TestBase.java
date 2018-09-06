package com.crm.qa.base;

/*
 * @author Poornima Bhat
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtils;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	
	public static Properties prop;
	
	public  static EventFiringWebDriver e_driver;
	
	public static WebEventListener eventListener;
	
	static Logger log = Logger.getLogger(TestBase.class);
	
	public TestBase() 
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\03 JAVA WORKSPACE\\01 WORKSPACE_02\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void initialization()
	{
		log.info("*************************start of the suite************************");
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\07 SELENIUM DOCS\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\07 SELENIUM DOCS\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
}
