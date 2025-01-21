package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLoginPage extends BasePage{

	public WebDriver dirver;
	
	public AccountLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btnlogin;
	
	@FindBy(xpath = "//div[@class='mb-3']//a[normalize-space()='Forgotten Password']")
	WebElement btnforgetpassword;
	
	public void setEmail(String email)
	{
		txtEmailAddress.sendKeys(email);
	}
	public void setPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	public void clickbtnLogin()
	{
		btnlogin.click();
	}
	
	public void clickForgetPassword()
	{
		btnforgetpassword.click();
	}
	
}
