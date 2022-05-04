package maven.project;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class FirstClass extends SecondClass {
    @Test
	public void A() {
    boolean b=true;
    Assert.assertTrue(b);
    Assert.assertFalse(b);
	System.out.println("A");	
	}
    @Test
    public void B() {
    	String a="Tanaji";
    	String b="Tanaji";
    	Assert.assertEquals(a, b);
    	Assert.assertNotEquals(a, b);
	System.out.println("B");	
	}
    @Test
    public void C() {
	System.out.println("C");
	}
   
}
    
