package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.AccountRegistrationPage;
import pageObjects.EndToEndPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC004_EndToEndTest extends BaseClass{
	
	@Test
	public void verify_EndToEnd() throws InterruptedException {

		//Registration
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Now inside of Registration page");
		
		AccountRegistrationPage accrp = new AccountRegistrationPage(driver);
		
		accrp.setFirstName(randomString().toUpperCase());
		accrp.setLastName(randomString().toUpperCase());
		
		String useremail = randomString()+"@gmail.com";
		System.out.println("User Email:"+useremail);
		accrp.setEmail(useremail);
		
		String userpassword = randomPassword();
		System.out.println("User Password: "+userpassword);
		accrp.setPassword(userpassword);
		
		logger.info("Fname, Lname, Email, Password data done in Registration page");
		accrp.setPrivacyPolicy();
		accrp.clickContinue();
		accrp.clickcontinuelast();
	
		MyAccountPage maccp = new MyAccountPage(driver);
		maccp.clickLogout();
		System.out.println("Registration Done");

		
		// login
		AccountLoginPage acclp = new AccountLoginPage(driver);

		try {
		hp.clickMyAccount();
		hp.clicklogin();
		
		acclp.setEmail(useremail);
		acclp.setPassword(userpassword);
		acclp.clickbtnLogin();
		
		boolean targetPage = maccp.isMyAccountPageExists();
		Assert.assertEquals(targetPage, true,"Login Failed!..");
		}
		catch (Exception e) {
			Assert.fail();
		}
		System.out.println("Login Done");
		
//		Thread.sleep(5000);
		//end to end
		EndToEndPage ete = new EndToEndPage(driver);
		System.out.println("Enter ete page");
		ete.clickCameras();
		ete.clickTablets();
		ete.clickLB();
		ete.clickAllLB();
		ete.clickmacbook();
		ete.clickAddToCart();
		ete.clickShoppingCart();			
		ete.clickCheckOut();
	
		//address
		System.out.println("going to add address");
		ete.ClickCheckboxAddNewAddress();
		ete.setAddFirstName(randomString().toUpperCase());
		ete.setAddLastName(randomString());
		ete.setAddCompany(randomString());
		ete.setAddOne(randomString());
		ete.setAddTwo(randomString());
		ete.setAddCity(randomString());
		ete.setAddPostalCode(randomString());
		ete.setAddCountry("Canada");
		ete.setAddZone("Ontario");
		ete.clickCheckoutContinue();
		
		ete.clickChooseShippingRate();
		ete.clickChooseShippingRateContinue();
		ete.clickPaymentMethods();
		ete.clickPaymentMethodsContinue();
		ete.setOderComment("Please Handle With Care....");
		
		ete.ClickConfirmOrder();
		Thread.sleep(1000);
		String conMsg = ete.getConfirmOrderMsg();
		if(conMsg.equals("Your order has been placed!")) {
			logger.error("Order is Confrimed");
			ete.ClickConfirmOrderContinue();
		}else {
			Assert.assertTrue(false);
			logger.error("Order is not Confrimed!....");
		}
		
		hp.clickMyAccount();
		maccp.clickMyAccount();
		System.out.println("Going to check Order History");
		maccp.clickViewYourOrderHistory();
		maccp.clickViewHistoryEyeIcon();
		maccp.clickViewOrderHistoryContinue();
		maccp.clickLogout();
		
		}
}
