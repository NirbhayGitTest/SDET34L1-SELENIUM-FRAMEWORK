package com.rmgyantra.projectTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInGUIWrtDatabaseTest {
	public static void main(String[] args) throws SQLException {
		Random r = new Random();
		int num = r.nextInt();
		String projectName = "SDET34L1";
		projectName = projectName+num;
		Driver d = new Driver();
		DriverManager.registerDriver(d);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement statement = connection.createStatement();
		statement.executeUpdate("insert into project value('TY_PROJ_"+num+"','Nirbhay','28/04/2022','"+projectName+"','on going','5')");
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8084");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.className("btn-success")).click();
		List<WebElement >projectList = driver.findElements(By.xpath("//table/tbody/tr/td"));
		
		for(WebElement list:projectList)
		{
			if(list.getText().equalsIgnoreCase(projectName))
			{
				System.out.println("project name is valid in gui");
				System.out.println("TC Pass");
			}
		}
		driver.quit();
	}
	
	

}
