package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtils;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	TestUtils testUtils;
	
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void SetUp() throws InterruptedException
	{
		initialization();
		testUtils = new TestUtils();
		loginPage = new LoginPage();		
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			
	}
	@Test(priority = 1)
	public void validateHomePageTitleTest()
	{
		String title = homePage.validateHomePageTitle();
		Assert.assertEquals(title, "CRMPRO");
	}
	
	@Test(priority = 2)
	public void validateUserDisplayTest()
	{
		testUtils.switchFrame();
		boolean flag = homePage.validateUserDisplay();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void clickContactsLinkTest()
	{
		testUtils.switchFrame();
		contactsPage = homePage.clickContactsLink();
	}
	
	@Test(priority = 4)
	public void clickDealsLinkTest()
	{
		testUtils.switchFrame();
		dealsPage = homePage.clickDealsLink();
	}
	
	@AfterMethod
	public void tearDown()
	{
		//loginPage = homePage.clickLogoutLink();
		driver.quit();
	}

}
