package com.sdet34l1.genericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains all common actions related to database
 * @author Nirbhay The Omen
 *
 */
public class DatabaseConnectionLibrary {
	
	static Connection connection;
	static Statement statement;
	
	/**
	 * This method is used to open the database connection and initialize the connection, statement
	 * @param dbUrl
	 * @param dbUserName
	 * @param dbPassword
	 * @throws SQLException
	 */
	public static void openDBConnection(String dbUrl, String dbUserName, String dbPassword) throws SQLException
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		statement = connection.createStatement();
	}

	/**
	 * This method is used to fetch the data from database to do the DQL actions on database
	 * @param query
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getDataFromDB(String query, String columnName) throws SQLException
	{
		ArrayList<String> list = new ArrayList<>();
		
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			list.add(result.getString(columnName));
		}
		return list;
	}
	
	
	/**
	 * This method is used to validate the data whether it is present in database or not
	 * @param query
	 * @param columnName
	 * @param excpectedData
	 * @return
	 * @throws SQLException
	 */
	public static boolean validateDataInDB(String query, String columnName, String excpectedData) throws SQLException
	{
		ArrayList<String> list = getDataFromDB(query, columnName);
		boolean flag = false;
		for(String actualData:list)
		{
			if(actualData.equalsIgnoreCase(excpectedData))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	
	/**
	 * This method is used to store/modify/insert/delete the data in database to do the DML and DDL actions on database
	 * @param query
	 * @throws SQLException
	 */
	public static void setDataInDB(String query) throws SQLException
	{
		int result = statement.executeUpdate(query);
		if(result>=1)
		{
			System.out.println("Data entered/modified successfully");
		}
		
	}
	
	/**
	 * This  method is used to close the Database connection
	 */
	public static void closeDBconnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("While closing the Database connection we got exception");
		}
	}
	
}
