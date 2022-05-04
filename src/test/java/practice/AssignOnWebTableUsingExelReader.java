package practice;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignOnWebTableUsingExelReader {
	WebDriver driver;
	
	@BeforeClass()
	public void setup() 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/webtables");	
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='rt-tbody']/div//div//div"));//table data
		int brows = ele.size();
		System.out.println("No of records before addition of details  "+brows);
	}

	@Test(dataProvider = "data")
	public void addRecords(String fName, String lName, String emailID, String age, String salary, String dept,String Exp) throws InterruptedException, IOException 
	{
		WebElement addbutton = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
		addbutton.click();
		driver.findElement(By.id("firstName")).sendKeys(fName);		
		driver.findElement(By.id("lastName")).sendKeys(lName);		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(emailID);		
		driver.findElement(By.xpath("//input[@id='age']")).sendKeys(age);		
		driver.findElement(By.xpath("//input[@id='salary']")).sendKeys(salary);		
		driver.findElement(By.xpath("//input[@id='department']")).sendKeys(dept);
		driver.findElement(By.xpath("//button[@id='submit']")).submit();
		String path = ".\\Datafiles\\Book2.xlsx";
		ExcelHelperClass help = new ExcelHelperClass(path);
		int totalRows = help.rowCount("Dec");
		Thread.sleep(1000);
		WebElement lastrow = driver.findElement(By.xpath("//div[@class='rt-tr-group']"));
		if(lastrow.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("All the records added succesfully");
		}
		else {
			Assert.assertTrue(false);
			System.out.println("Records not added");
		}		
    }
	@Test(priority=2)
	public void editRecords() 
	{
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='rt-tbody']/div//div//div"));//table data
		int arows = ele.size();
	    System.out.println("No of records after adding  details  "+arows);
		Actions act=new Actions(driver);
		WebElement edit = driver.findElement(By.xpath("//span[@id='edit-record-3']"));
		act.moveToElement(edit).click().build().perform();
		WebElement fn = driver.findElement(By.id("firstName"));
		fn.clear();
		fn.sendKeys("Tanaji");
		WebElement ln = driver.findElement(By.id("lastName"));
		ln.clear();
		ln.sendKeys("ghgkjj");
		WebElement id = driver.findElement(By.xpath("//input[@id='userEmail']"));
		id.clear();
		id.sendKeys("AAA@gmail.com");
		WebElement age = driver.findElement(By.xpath("//input[@id='age']"));
		age.clear();
		age.sendKeys("28");
		WebElement sal = driver.findElement(By.xpath("//input[@id='salary']"));
		sal.clear();
		sal.sendKeys("22000");
		WebElement dep = driver.findElement(By.xpath("//input[@id='department']"));
		dep.clear();
		dep.sendKeys("Finance");
		driver.findElement(By.xpath("//button[@id='submit']")).submit();
		
	}
	
	@Test(priority=3)
	public void deleteRecords()
	{
		int i=1;
	    while(i<=7)
		{
		Actions act=new Actions(driver);
	    WebElement del = driver.findElement(By.xpath("//span[@id='delete-record-"+i+"']"));
	    act.moveToElement(del).click().build().perform();
	    i++;
	    }	
	    WebElement confdelt = driver.findElement(By.xpath("//div[contains(text(),'No rows found')]"));
        boolean massage = confdelt.isDisplayed();
        if(massage==true)
          {
            Assert.assertTrue(massage);
            System.out.println("All records deleted succesfully");	
          }
        else
          {
    	    Assert.assertTrue(false);
    	    System.out.println("Record Deletion failed"); 
          }
	  }
	
	@DataProvider(name = "data")
	public String[][] getTestData() throws IOException {
		
		String path = ".\\Datafiles\\Book2.xlsx";
		ExcelHelperClass help = new ExcelHelperClass(path);
		int totalRows = help.rowCount("Dec");
		int totalcols = help.cellCount("Dec", 1);

		String data[][] = new String[totalRows][totalcols];

		for (int i = 1; i <= totalRows; i++)
		{
			for (int j = 0; j < totalcols; j++)
			{
				data[i - 1][j] = help.cellData("Dec", i, j);
			}
		}
		return data;
	}
}
