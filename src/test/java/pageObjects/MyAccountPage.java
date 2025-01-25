package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h1[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnlogoutcontinue;
	
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='My Account']")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//a[normalize-space()='View your order history']")
	WebElement btnViewYourOrderHistroy;

	@FindBy(xpath = "//i[@class='fa-solid fa-eye']")
	WebElement btnOrderHistoryEyeIcon;
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnContinueViewOrderHistory;
	
	public boolean isMyAccountPageExists() {
		try {return (msgHeading.isDisplayed());
		}catch (Exception e) {
			return false;		}
	}
	
	public void clickLogout() {
		HomePage hp = new HomePage(driver);
		hp.lnkMyaccount.click();
		lnkLogout.click();
		btnlogoutcontinue.click();
	}
	public void clickMyAccount() {
		lnkMyAccount.click();	}
	
	
	public void clickViewYourOrderHistory() {
		btnViewYourOrderHistroy.click();	}
	
	public void clickViewHistoryEyeIcon() {
		btnOrderHistoryEyeIcon.click();
	}
	
	public void clickViewOrderHistoryContinue() {
		Actions actions = new Actions(driver);
		actions.moveToElement(btnContinueViewOrderHistory).perform();
		btnContinueViewOrderHistory.click();
	}
}
