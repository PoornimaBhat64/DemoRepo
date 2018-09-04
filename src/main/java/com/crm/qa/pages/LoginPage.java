package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//OR - PageFactory
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath = "//img[(@class='img-responsive')and (@alt='free crm logo')]")
	WebElement crmLogo;
	
	//Initializing the page objects
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Methods
	public String validateLoginPage()
	{
		return driver.getTitle();
	}
	
	public boolean validateCRMLogo()
	{
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) throws InterruptedException
	{
		username.sendKeys(un);
		password.sendKeys(pwd); 
		
		Thread.sleep(2000);
		
		loginBtn.click();
		return new HomePage();
	}
	
	
}
