package maven.project;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SecondClass {

	 
	    @BeforeTest
	    public void beforeTest()  {
		System.out.println("Before Test");
	    }
	    @AfterTest
	    public void afterTest()  {
	    	System.out.println("AfterTest");
	        }
	    @BeforeMethod
	    public void beforeMethod()  {
	    	System.out.println("beforeMethod");
	        }
	    @AfterMethod
	    public void afterMethod()  {
	    	System.out.println("afterMethod");
	        }
	    @BeforeClass
	    public void beforeClass()  {
	    	System.out.println("beforeClass");
	        }
	    @AfterClass
	    public void afterClass()  {
	    	System.out.println("afterClass");
	        }

}


