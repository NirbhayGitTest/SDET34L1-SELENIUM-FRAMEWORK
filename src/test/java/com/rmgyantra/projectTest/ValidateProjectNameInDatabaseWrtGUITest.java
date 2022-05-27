package com.rmgyantra.projectTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericLibrary.DatabaseConnectionLibrary;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInDatabaseWrtGUITest {

	public static void main(String[] args) throws IOException, SQLException {
		
		JavaLibrary javaLibrary = new JavaLibrary();
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.RMGYANTRAPROPERTYFILEPATH);
		String dbUrl = PropertyFileLibrary.getDataFromPropertyFile("dbUrl");
		String rmgUserName = PropertyFileLibrary.getDataFromPropertyFile("rmgUserName");
		String rmgPassword = PropertyFileLibrary.getDataFromPropertyFile("rmgPassword");
		String dbName = PropertyFileLibrary.getDataFromPropertyFile("dbName");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(dbUrl);
		driver.findElement(By.id("usernmae")).sendKeys(rmgUserName);
		driver.findElement(By.id("inputPassword")).sendKeys(rmgPassword);
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		
		String projectName = "SDET34L1-ty"+javaLibrary.getRandomNumber(1000);
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("document.getElementsByName=('teamsize').value'7'");
		
		driver.findElement(By.name("createdBy")).sendKeys("Deepak");
		
		WebElement projectStatusDropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		
		Select s = new Select(projectStatusDropdown);
		
		s.selectByVisibleText("On Goging");
		
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
//		Connection connection = null;
//		
//		try {
//			Driver d = new Driver();
//			DriverManager.registerDriver(d);
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
//			Statement statement = connection.createStatement();
//			ResultSet result = statement.executeQuery("select project_name from project;");
//			
//			
//			while(result.next())
//			{
//				if(result.getString("project_Name").equalsIgnoreCase(projectName))
//				{
//					System.out.println("Project name is present in the database");
//					System.out.println("TC Pass");
//					break;
//				}
//				
//			}
//		} 
//		
//		
//		catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}		
//		
//		finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println(e.getMessage());
//			}
//			
//		}
		
		DatabaseConnectionLibrary.openDBConnection
		(IallPathDataLibrary.DATABASEURL+PropertyFileLibrary.getDataFromPropertyFile("dbName"),
				PropertyFileLibrary.getDataFromPropertyFile("dbUserName"),
				PropertyFileLibrary.getDataFromPropertyFile("dbPassword"));
		
		
		
		
		boolean status = DatabaseConnectionLibrary.validateDataInDB("select project_name from project;", "project_name", projectName);
		
		//or if(status)
		if(status == true)
		{
			System.out.println("TC Pass");
		}
		else
		{
			System.out.println("TC Fail");
		}
		
		DatabaseConnectionLibrary.closeDBconnection();
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
	}
}
