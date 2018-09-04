package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath = "//td[contains(text(),'User: POORNIMA BHAT')]")
	WebElement userNameDisplay;
	
	@FindBy(xpath = "//a[@title = 'Contacts']")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[@title = 'Deals']")
	WebElement dealsLink;
	
	@FindBy(xpath = "//i[@class = 'fa fa-sign-out icon-2x']")
	WebElement logoutLink;
	
	@FindBy(xpath = "//a[@title='New Contact']")
	WebElement newContact;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateUserDisplay()
	{
		return userNameDisplay.isDisplayed();
	}
	
	public ContactsPage clickContactsLink()
	{
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickDealsLink()
	{
		dealsLink.click();
		return new DealsPage();
	}
	
	public LoginPage clickLogoutLink()
	{
		logoutLink.click();
		return new LoginPage();
	}
	public void clickOnNewContact() {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContact.click();
		
	}
	
}
