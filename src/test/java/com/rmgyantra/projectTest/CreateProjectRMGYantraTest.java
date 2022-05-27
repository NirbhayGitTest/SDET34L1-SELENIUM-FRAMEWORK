package com.rmgyantra.projectTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjectRMGYantraTest {
	public static void main(String[] args) throws SQLException {
		Random r = new Random();
		int num = r.nextInt(1000);
		String actual = "SDET34_"+num;
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra",Keys.TAB,"rmgy@9999",Keys.ENTER);
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.className("btn-success")).click();
		driver.findElement(By.name("projectName")).sendKeys(actual);
		 ((JavascriptExecutor)driver).executeScript("document.getElementsByName('teamSize').values='5';");
		 driver.findElement(By.name("createdBy")).sendKeys("Mohan sir");
		 WebElement wb = driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
		 Select s = new Select(wb);
		 s.selectByVisibleText("On Goging");
		 driver.findElement(By.cssSelector("[value='Add Project']")).click();
		 
		 String expected=driver.findElement(By.className("table-hover")).getText();
		// System.out.println(expected);
			Driver driver1=new Driver();
			DriverManager.registerDriver(driver1);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			Statement statement=connection.createStatement();
			ResultSet result=statement.executeQuery("select * from project");
			while(result.next())
			{
				String s1=(result.getString(4));
				if(s1.equals(actual))
				{
				System.out.println("passed :");
				}
				else
				{
					System.out.println("Fail");
				}
			}
			connection.close();
			driver.close();
		
	}

}