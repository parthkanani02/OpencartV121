package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.BaseClass;

//left FOR ---- screen shot // timeStemp file- for backup

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // create test case entries in the report and update status of the methods
	String repName;
	
	public  void onStart(ITestContext context) {
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.MM.ss"); 
		Date dt =new Date(); 
		String currentdatetimestamp = df.format(dt);
		 */
//		file:/C:/Workspaces/Eclips/opencartV121/null/reports/Test-Report-2025.01.1
//		9.05.01.41.html
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.MM.ss").format(new Date());
		repName = "Test-Report-" + timeStamp +".html";
		
//		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//"+repName);
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");// Title of Report
		sparkReporter.config().setReportName("Opencart Functional Testing");// name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module","Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Enviourment","QA");
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser Name", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getClass().getName()); // create a new entry in report
		test.assignCategory(result.getMethod().getGroups());// to display groups in reports
		test.log(Status.PASS,result.getName()+" got successfully executed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
	
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	/**
	 *
	 */
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName); 
			
			//Create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("parthkananisocial@gmail.com","Password"));
			email.setSSLOnConnect(true);
			email.setFrom("parthkananisocial@gmail.com"); // sender
			email.setSubject("Test Result");
			email.setMsg("Please find Attached Report...");
			email.addTo("parthkananisocial@gmail.com"); // Receiver
			email.attach(url, "extent report", "Please Check Report...");
			email.send(); // send the email
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
}
