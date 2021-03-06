package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtils;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtils testUtils;
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		testUtils = new TestUtils();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtils.switchFrame();
		contactsPage = homePage.clickContactsLink();
	} 
	@Test(priority = 1)
	public void validateContactsLabelTest()
	{
		boolean flag = contactsPage.validatecontactsLabel();
		Assert.assertTrue(flag);
	}
	@Test(priority = 2)
	public void selectSingleContactsTest()
	{
		contactsPage.selectContactsByName("rohith bhat");
	}
	@Test(priority = 2)
	public void selectMultipleContactsTest()
	{
		contactsPage.selectContactsByName("rohith bhat");
		contactsPage.selectContactsByName("test2 test2");
	} 
	@DataProvider
	public Object[][] crmTestData()
	{
		Object[][] data = testUtils.getCellData("contacts");
		return data;
	}
	@Test(priority=3, dataProvider="crmTestData")
	public void validateCreateNewContact(String title, String firstname,String lastname, String company)
	{
		homePage.clickOnNewContact();
		contactsPage.createNewContact(title, firstname, lastname, company);
		contactsPage.clickOnSave();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
