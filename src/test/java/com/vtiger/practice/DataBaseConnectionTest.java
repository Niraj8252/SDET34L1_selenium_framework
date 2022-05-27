package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnectionTest {
	public static void main(String[] args) throws SQLException {
		//Step_01:- object creation of Implimentation class
		Driver driver=new Driver();
		
		//Step_02:-Register the driver with jdbc
		DriverManager.registerDriver(driver);
		
		//Step_03:-Establish database connection
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/SDET34L1", "root", "root");
		
		//Steps_04:- Create statement
		Statement statement = connection.createStatement();
		//Step_05:- Execute the query
		ResultSet result =statement.executeQuery("select * from emp;");
		//Step_06:- Validate
		while(result.next())
		{
			System.out.println(result.getString("ename"));
		}
		//Step_07:- close connection
		connection.close();
	}

}
