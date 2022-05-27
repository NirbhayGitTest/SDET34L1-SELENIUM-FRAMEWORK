package com.sdet34l1.genericLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebElement;

/**
 * This class contains only java specific reusable methods
 * @author Nirbhay The Omen
 *
 */

public class JavaLibrary {
	
	/**
	 * This method is used to Convert String value to long datatype
	 * @param value
	 * @return
	 */
	public long stringToLong(String value)
	{
		return Long.parseLong(value);
	}
	
	/**
	 * This method used to get the random Number
	 * @param limit
	 * @return
	 */
	public int getRandomNumber(int limit)
	{
		Random ran = new Random();
		return ran.nextInt(limit);
	}
	
	/**
	 * This method is used to print the message
	 * @param message
	 */
	public void printStatement(String message)
	{
		System.out.println(message);
	}
	
	/**
	 * This method is used to validate if condition based on equals
	 * @param actualresult
	 * @param expectedResult
	 * @param testCaseName
	 */
	public void assertionThroughIfCondition(String actualresult, String expectedResult, String testCaseName)
	{
		if(actualresult.equalsIgnoreCase(expectedResult))
		{
			System.out.println(testCaseName +" created successfully");
			System.out.println("TC Pass");
		}
	}

	
	/**
	 * This method is used to wait until the element is Clickable
	 * @param element
	 * @param pollingTime
	 * @param duration
	 * @throws InterruptedException
	 */
	public void customWait(WebElement element, long pollingTime, int duration) throws InterruptedException
	{
		int count = 0;
		while(count<=duration)
		{
			try 
			{
				element.click();
				break;
			}
			catch(Exception e)
			{
				Thread.sleep(pollingTime);
				count++;
			}
		}
	}
	
	/**
	 * This method is used to display the Date and Time according our given format
	 * @return
	 */
	
	public String getDateTimeInFormat()
	{
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_sss").format(new Date());
	}

}
