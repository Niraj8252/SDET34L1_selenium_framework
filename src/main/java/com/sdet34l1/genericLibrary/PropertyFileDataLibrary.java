package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains property file spacific common methods
 * @author Niraj
 *
 */
public class PropertyFileDataLibrary {
	
	static Properties property;
	/**
	 * This method is used to open property file
	 * @throws IOException 
	 */
	
	public static void openPropertyFile(String filePath) throws IOException
	{
		FileInputStream fis= new FileInputStream(filePath);
		property=new Properties();
		property.load(fis);
	}
	
	/**
	 * This method is used to fetch data from property file
	 * @param key
	 * @return
	 */
	public static String getDataFromPropertyFile(String key)
	{
		String value=property.getProperty(key);
		return value;
	}
}
 