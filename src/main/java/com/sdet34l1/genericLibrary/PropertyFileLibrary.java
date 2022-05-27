package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains only Property file Specific common methods
 * @author Nirbhay The Omen
 *
 */
public class PropertyFileLibrary {
	
	static Properties property;
	/**
	 * This method is used to open the property file
	 * @param filePath
	 * @throws IOException 
	 */
	public static void openPropertyFile(String filePath) throws IOException
	{
		FileInputStream fis = new FileInputStream(filePath);
		property = new Properties();
		property.load(fis);
	}
	
	/**
	 * This method is used to fetch the data from the Property File
	 * @param key
	 * @return
	 */
	public static String getDataFromPropertyFile(String key)
	{
		String value = property.getProperty(key);
		return value;
	}

}
