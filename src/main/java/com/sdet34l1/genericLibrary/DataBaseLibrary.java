package com.sdet34l1.genericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;


/**
 * This class is contains database related specific methods 
 * @author Niraj
 *
 */
public class DataBaseLibrary {
	static Driver driver;
	static Connection connection;
	static Statement statement;
	
	/**
	 * This method is used to open the database
	 * @param dbUrl
	 * @param dbUserName
	 * @param dbPassword
	 * @throws SQLException
	 */
	public static void openDataBaseConnection(String dbUrl,String dbUserName,String dbPassword) throws SQLException
	{
		driver = new Driver();
		DriverManager.registerDriver(driver);
		connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		statement=connection.createStatement();
	}
	
	/**
	 * This methos is used to close the database connection
	 */
	public static void closeDataBaseConnection() 
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("we got exception while closing the database");
		}
	}
	
	/**
	 * This method is used to fetch the data from database
	 * @param query
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getDataFromDataBase(String query,String columnName) throws SQLException
	{
		ArrayList<String> list= new ArrayList<>();
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			list.add(result.getString(columnName));
		}
		return list;
	}
	
	/**
	 * This method is used to validate the data into database
	 * @param query
	 * @param clumnName
	 * @param expectedData
	 * @return
	 * @throws SQLException
	 */
	public static boolean validateDataInDataBase(String query,String clumnName,String expectedData) throws SQLException
	{
		ArrayList<String> list=getDataFromDataBase(query, clumnName);
		boolean flag=false;
		for(String actualData:list)
		{
			if(actualData.equalsIgnoreCase(expectedData))
			{
				
		flag=true;
		break;
		}
	}
	return flag;
	}
	
	
	/**
	 * This method is used to write the data into database
	 * @param query
	 * @throws SQLException
	 */
	public static void setDataInDataBase(String query) throws SQLException
	{
		int result = statement.executeUpdate(query);
		if(result>=1)
		{
			System.out.println("Data entered/modified successfully");
		}
	}
}


