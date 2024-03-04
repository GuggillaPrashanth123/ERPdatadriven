package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil {
	String inputpath = "./FileInput/LoginData.xlsx";
	String outputpath ="./FileOutput/DataDrivenResults.xlsxS";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void starttest() throws Throwable
	{
		report = new ExtentReports("./target/Reports/LoginReports.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowcount("Login");
		Reporter.log("no of rows::"+rc,true);
		for(int i =1 ;i<=rc;i++)
		{
			logger = report.startTest("validate login");
			logger.assignAuthor("prashanth");
			String username = xl.getcelldata("Login", i, 0);
			String password = xl.getcelldata("Login", i, 1);
			logger.log(LogStatus.INFO, username+"  "+password);
			boolean res = FunctionLibrary.adminLogin(username, password);
					if(res)
					{
						xl.setcelldata("login", i, 2, "Loginsucess",outputpath);
						xl.setcelldata("login", i, 3, "pass",outputpath);
						logger.log(LogStatus.PASS,"username and password are valid");
					}
					else
					{
						File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(screen,new File("./screenshot/Iteration/"+i+"Loginpage.png"));
						xl.setcelldata("login", i, 2, "LoginFail",outputpath);
						xl.setcelldata("login", i, 3, "fail",outputpath);
						logger.log(LogStatus.FAIL,"username and password are not  valid");
							}
			report.endTest(logger);
			report.flush();
			
		}
	}
			

}

















