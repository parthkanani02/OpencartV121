package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class AccountRegistrationPage extends BasePage{
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msfConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	
	}
	
	public void setPrivacyPolicy() {
		Actions act = new Actions(driver);
		act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
		act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
		act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
		act.keyDown(Keys.SPACE).keyUp(Keys.SPACE).perform();
//		chkPolicy.click();
	}
	
	public void clickContinue(){
	System.out.println("Inside of ClickContinue");
//	Actions act = new Actions(driver);
//	act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
//	act.keyDown(Keys.SPACE).keyUp(Keys.SPACE).perform();

//	1) solution
//	btnContinue.click();
	
//	2)
	btnContinue.submit();
		
//	3)
//	Actions act = new Actions(driver);
//	act.moveToElement(btnContinue).click().build().perform();
	
//	4)
//	JavascriptExecutor js= (JavascriptExecutor)driver;
//	js.executeScript("argument[0].click();", btnContinue);
	
//	5)
//	btnContinue.sendKeys(Keys.RETURN);
	
//	6)
//	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	mywait.until(ExpectedConditions.elementToBeClickable(btnContinue).click());
		
	}
	
	public String getConfirmationMsg() 
	{
		try {
		return(msfConfirmation.getText());
		}
		catch (Exception e) {
			return (e.getMessage());
		}
	}
	
}
