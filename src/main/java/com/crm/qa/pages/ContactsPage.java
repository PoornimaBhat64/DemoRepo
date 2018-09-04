package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	//a[contains(text(),'rohith bhat')]/parent::td/preceding-sibling::td/input[contains(@name,'contact_id')]
	
	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id = "first_name")
	WebElement firstName;
	
	@FindBy(id= "surname")
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(xpath = "//input[@type='button' and @value = 'Load From Company']//following-sibling::input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean validatecontactsLabel()
	{
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name)
	{
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]/"
				+ "parent::td/preceding-sibling::td/input[contains(@name,'contact_id')]")).click();
	}
	public void createNewContact(String title,String fName,String lName,String compName)
	{
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		company.sendKeys(compName);
		
	}
	public void clickOnSave()
	{
		saveBtn.click();
	}
	
}
