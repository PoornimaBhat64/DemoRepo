package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import com.crm.qa.base.TestBase;

public class TestUtils extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	
	public static long IMPLICIT_WAIT = 20;
	
	public static String TEST_DATA_PATH = "D:\\03 JAVA WORKSPACE\\01 WORKSPACE_02\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\TestData_CRM_Test.xlsx";
	
	static Workbook book;
	
	static Sheet sheet;
	
	public void switchFrame()
	{
		driver.switchTo().frame("mainpanel");
	}
	
	public Object[][] getCellData(String sheetName)
	{
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(TEST_DATA_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()] [sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i< sheet.getLastRowNum();i++)
		{
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++)
			{
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}
		
		return data;
		
	}
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		System.out.println(currentDir);
		
		FileUtils.copyFile(scrFile, new File(currentDir + "\\Screenshots\\" + System.currentTimeMillis() + ".png"));
		
		}
	
}
