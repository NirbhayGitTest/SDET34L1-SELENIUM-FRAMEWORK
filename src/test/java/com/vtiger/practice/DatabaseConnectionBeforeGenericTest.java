package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseConnectionBeforeGenericTest {
	
	public static void main(String[] args) throws SQLException {
		
		//Create object for Implementation class
		Driver driver = new Driver();
		
		//Register the driver with jdbc
		DriverManager.registerDriver(driver);
		
		//Establish the database connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet34l1", "root", "root");
		
		//Create statement
		Statement statement = connection.createStatement();
		
		//Execute query
		ResultSet result = statement.executeQuery("select * from emp;");
		
		//validate (based on test case)
		while(result.next())
		{
			//System.out.println(result.getString(2));
			//or- System.out.println(result.getString(2));
			System.out.println(result.getString("ename")+" "+result.getString("job"));
		}
		
		//close the connection
		connection.close();
	}

}