package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ctc.wstx.shaded.msv_core.grammar.xmlschema.XPath;


public class EndToEndPage extends BasePage{

	public EndToEndPage(WebDriver driver) {
		super(driver);	
	}
	@FindBy(xpath =  "//a[@class='dropdown-item'][normalize-space()='Cameras']")
	WebElement  txtCameras;
	
	@FindBy(xpath =  "//a[normalize-space()='Tablets']")
	WebElement  txtTablets;
	
	@FindBy(xpath =  "//a[normalize-space()='Laptops & Notebooks']")
	WebElement  txtLaptopNDnotebook;

	@FindBy(xpath =  "//a[normalize-space()='Show All Laptops & Notebooks']")
	WebElement  txtAllLapandbook;
	
	@FindBy(xpath = "//div[@class='image']//img[@title='MacBook Air']")
	WebElement imgMacbook;
	
	@FindBy(xpath =  "//button[@id='button-cart']")
	WebElement  btnaddtocart;
	
	@FindBy(xpath =  "//span[normalize-space()='Shopping Cart']")
	WebElement  btnshoppingcart;

	@FindBy(xpath =  "//a[@class='btn btn-primary']")
	WebElement  btncheckout;
	
	// Particular product compare
	@FindBy(xpath =  "//i[@class='fa-solid fa-arrow-right-arrow-left']")
	WebElement  btnItemCompare;
	
	@FindBy(xpath =  "//a[normalize-space()='product comparison']")
	WebElement  btnItemCompareList;
	
	// Checkout Address 
	@FindBy(xpath =  "//input[@id='input-shipping-new']")
	WebElement  checkboxAddNewAddress;	
	@FindBy(xpath =  "//input[@id='input-shipping-firstname']")
	WebElement  txtAddFirstName;	
	@FindBy(xpath =  "//input[@id='input-shipping-lastname']")
	WebElement  txtAddLastName;	
	@FindBy(xpath =  "//input[@id='input-shipping-company']")
	WebElement  txtAddCompany;	
	@FindBy(xpath =  "//input[@id='input-shipping-address-1']")
	WebElement  txtAddOne;	
	@FindBy(xpath =  "//input[@id='input-shipping-address-2']")
	WebElement  txtAddTwo;	
	@FindBy(xpath =  "//input[@id='input-shipping-city']")
	WebElement  txtAddCity;	
	@FindBy(xpath =  "//input[@id='input-shipping-postcode']")
	WebElement  txtAddPostalCode;	
	
	@FindBy(xpath =  "//select[@id='input-shipping-country']")
	WebElement  txtAddCountry;	
	@FindBy(xpath =  "//select[@id='input-shipping-zone']")
	WebElement  txtAddZone;	
	
	@FindBy(xpath =  "//button[@id='button-shipping-address']")
	WebElement  btnCheckoutContinue;	
	
	@FindBy(xpath =  "//button[@id='button-shipping-methods']")
	WebElement  btnShippingRate;
	@FindBy(xpath =  "//button[@id='button-shipping-method']")
	WebElement  btnShippingRateContinue;
	
	@FindBy(xpath =  "//button[@id='button-payment-methods']")
	WebElement  btnPaymentMethod;
	@FindBy(xpath =  "//button[@id='button-payment-method']")
	WebElement  btnPaymentMethodContinue;
	
	@FindBy(xpath =  "//textarea[@id='input-comment']")
	WebElement  txtareaOrderComments;
	
	@FindBy(xpath =  "//button[@id='button-confirm']")
	WebElement  btnConfirmOrder;
	
	@FindBy(xpath =  "//h1[normalize-space()='Your order has been placed!']")
	WebElement  msgConfirmOrder;
	
	@FindBy(xpath =  "//a[normalize-space()='Continue']")
	WebElement  btnConfirmOrderContinue;
	
	
	public void clickCameras() {
		txtCameras.click(); }
	
	public void clickTablets() {
		txtTablets.click(); }
	
	public void clickLB() {
		txtLaptopNDnotebook.click(); }
	
	public void clickAllLB() {
		txtAllLapandbook.click(); }
	
	public void clickmacbook() {
		imgMacbook.click(); }
	
	public void clickAddToCart() {
		btnaddtocart.click();  }
	
	public void clickShoppingCart() {
		btnshoppingcart.click(); }
	
	public void clickCheckOut() {
		Actions actions = new Actions(driver);
		actions.moveToElement(btncheckout).perform();
		btncheckout.click();	}
	
	public void ClickCheckboxAddNewAddress() {
		if(checkboxAddNewAddress.isDisplayed())
		{
		checkboxAddNewAddress.click(); 
		}}
	
	public void setAddFirstName(String addfirstname) {
		txtAddFirstName.sendKeys(addfirstname); }

	public void setAddLastName(String addlastname) {
		txtAddLastName.sendKeys(addlastname); }

	public void setAddCompany(String addcompany) {
		txtAddCompany.sendKeys(addcompany); }

	public void setAddOne(String addone) {
		txtAddOne.sendKeys(addone); }

	public void setAddTwo(String addtwo) {
		txtAddTwo.sendKeys(addtwo); }

	public void setAddCity(String addcity) {
		txtAddCity.sendKeys(addcity); }

	public void setAddPostalCode(String addpostalcode) {
		txtAddPostalCode.sendKeys(addpostalcode); }
	
	public void setAddCountry(String addcountry) {
		Select drpcountry = new Select(txtAddCountry);
		drpcountry.selectByVisibleText(addcountry);}
	
	public void setAddZone(String addzone) {
		Select drpzone = new Select(txtAddZone);
		drpzone.selectByVisibleText(addzone); }
	
	public void clickCheckoutContinue() {
		Actions actions = new Actions(driver);
		actions.moveToElement(btnCheckoutContinue).perform();
		btnCheckoutContinue.click(); }
	
	public void clickChooseShippingRate() {
		btnShippingRate.click(); }
	
	public void clickChooseShippingRateContinue() {
		btnShippingRateContinue.click(); }
	
	public void clickPaymentMethods() {
		btnPaymentMethod.click(); }
	
	public void clickPaymentMethodsContinue() {
		btnPaymentMethodContinue.click(); }

	public void setOderComment(String odercomm) {
		txtareaOrderComments.sendKeys(odercomm); }
	
	public void ClickConfirmOrder() {
		Actions actions = new Actions(driver);
		actions.moveToElement(btnConfirmOrder).perform();
		btnConfirmOrder.click(); }
	
	public String getConfirmOrderMsg() {
		try{
			String msg = msgConfirmOrder.getText();
			return msg;
		}catch (Exception e) {
			return e.getMessage();
		}
	}

	public void ClickConfirmOrderContinue() {
		btnConfirmOrderContinue.click(); }

		
}
