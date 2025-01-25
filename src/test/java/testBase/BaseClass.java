package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.apache.logging.log4j.LogManager; // Careful while adding import for log (log4j)
import org.apache.logging.log4j.Logger; // Careful while adding import for log (log4j)

public class BaseClass {

	public static WebDriver driver;	
	public JavascriptExecutor js;
	public Logger logger; //log4j
	public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		//Loading config.propertis file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		// Careful while adding import for log (log4j)
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equals("remote"))
		{
			 DesiredCapabilities capabilities = new DesiredCapabilities(); 
			 
			 //os
			 if(os.equalsIgnoreCase("Window"))
			 {
				 capabilities.setPlatform(Platform.WIN10);
			 }
			 else if (os.equalsIgnoreCase("mac"))
			 {
				 capabilities.setPlatform(Platform.MAC);
			 }
			 else if (os.equalsIgnoreCase("linux"))
			 {
				 capabilities.setPlatform(Platform.LINUX);
			 }
			 else {
				 System.out.println("No Matching OS");
				 return;
			 }
			 
			 // browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No Matching Browser"); return;
			}
			
			driver = new RemoteWebDriver( new URL ("http://localhost:4444/wd/hub"),capabilities);
			
		}
		
		if(p.getProperty("execution_env").equals("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid Browser Name..."); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); // reading URL from properties file
		System.out.println("url opened");
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void tearDown()
	{
		System.out.println("window url -- closed");
		driver.quit();
	}
	
	public String randomString() {
		// from commons library- dependency
		String genstring =RandomStringUtils.randomAlphabetic(5);
		return genstring;
	}
	
	public String randomNum() {
		// from commons library- dependency
		String gennum=RandomStringUtils.randomNumeric(5);
		return gennum;
	}
	public String randomPassword() {
		// from commons library- dependency
		String genstring =RandomStringUtils.randomAlphabetic(3);
		String gennnum =RandomStringUtils.randomAlphanumeric(3);		
		return (genstring+"@"+gennnum);
	}
	
	public String captureScreen(String tname) throws IOException{

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath =  System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
