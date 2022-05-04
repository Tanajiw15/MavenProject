package practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyingAddEditDeletTableEle {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/webtables");
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='rt-tbody']/div//div//div"));//table data
		int rows = ele.size();
		System.out.println("Number of records in table are- "+rows);//size of table
		System.out.println("--------------------------");
		WebElement addbutton = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));//adding details
		addbutton.click();
		driver.findElement(By.id("firstName")).sendKeys("abc");
		driver.findElement(By.id("lastName")).sendKeys("asdf");
		WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
		email.sendKeys("name@gmail.com");
		String id = email.getAttribute("value");
		driver.findElement(By.xpath("//input[@id='age']")).sendKeys("20");
		driver.findElement(By.xpath("//input[@id='salary']")).sendKeys("20000");
		driver.findElement(By.xpath("//input[@id='department']")).sendKeys("IT");
		driver.findElement(By.xpath("//button[@id='submit']")).submit();//submitted new details
		String emailID = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//div//div[4]")).getText();
		String fName = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//div//div[1]")).getText();
		String lName = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//div//div[2]")).getText();
		String age = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//div//div[3]")).getText();
		String Salary = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//div//div[5]")).getText();
		String department = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//div//div[6]")).getText();
		System.out.println("New added user details--"+fName+"        "+lName+"     "+age+"      "+Salary+"       "+department);
	    System.out.println("------------------------");
	    
	    if(emailID.equals(id)) 
	    {
	    	System.out.println("User details added succesfully");//............verify added details
	    	
	    	driver.findElement(By.xpath("//span[@id='edit-record-4']")).click();//.....for edit 
	    	WebElement sal = driver.findElement(By.xpath("//input[@id='salary']"));
	    	sal.clear();
	    	sal.sendKeys("20000");//.........edited
			WebElement dept = driver.findElement(By.xpath("//input[@id='department']"));
			dept.clear();
			dept.sendKeys("Technical");//............edited
	    	driver.findElement(By.xpath("//button[@id='submit']")).submit();//....edit details submitted
	    	String editdept = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//div//div[6]")).getText();
	    	System.out.println("-----------------------");
	    	System.out.println("Updated user dept details- "+editdept);
	    	
	    if(editdept.equals("Technical")) 
	    {
	    	System.out.println("User details updated succesfully");//.........massage
	    	
            driver.findElement(By.xpath("//span[@id='delete-record-1']")).click();//.....deleted records
            driver.findElement(By.xpath("//span[@id='delete-record-2']")).click();
            driver.findElement(By.xpath("//span[@id='delete-record-3']")).click();
            driver.findElement(By.xpath("//span[@id='delete-record-4']")).click();
            Thread.sleep(3000);
            WebElement ele1 = driver.findElement(By.xpath("//div[contains(text(),'No rows found')]"));
            System.out.println(ele1.isDisplayed());//......checked massage
            boolean massage = ele1.isDisplayed();
           
		if(massage==true)
            {
            System.out.println("All User details deleted succesfully");	
            }
            
	    }
	   
	  }
	}
}