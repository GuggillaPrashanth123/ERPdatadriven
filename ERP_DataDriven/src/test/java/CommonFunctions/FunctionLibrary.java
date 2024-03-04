package CommonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil
{
	public static boolean adminLogin(String user,String pass)throws Throwable
	{
	driver.get(conpro.getProperty("Url"));	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.findElement(By.xpath(conpro.getProperty("Objresetbutton"))).click();
	driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
	driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
	driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
	Thread.sleep(2000);
	String  Expected = "dashboard";
	String Actual = driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		Reporter.log("valid username and password::"+Expected+"    "+Actual,true);
		driver.findElement(By.xpath(conpro.getProperty("Objlogout"))).click();
		return true;
	}
	else
	{
		String message = driver.findElement(By.xpath(conpro.getProperty("Objmessage"))).getText();
		Thread.sleep(2000);
		driver.findElement(By.xpath(conpro.getProperty("Objokbutton"))).click();
		Reporter.log(message+"  "+Expected+"  "+Actual,true);
		return false;
	 }
	}
  }














