package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Datadriven") // data provider from different class
	public void verify_LoginDDT(String email,String pwd, String exp) {
		
		logger.info("***** Staring TC003_LoginDDT ***** ");
		try {
			//Home Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clicklogin();
			
			// Login Page
			AccountLoginPage lp = new AccountLoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickbtnLogin();
			
			// My Account
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();
			
	//		 Data is valid  - login success - test pass - logout
	//						- login failed - test failed
	
	//		 Data is Invalid- login success - test Failed - logout
	//						- login failed - test pass
			
			if(exp.equalsIgnoreCase("Valid")){
				
				if(targetPage== true)
				{
					Assert.assertTrue(true);
					macc.clickLogout();
				}
				else {
					Assert.assertTrue(false);
				}
			}
			else if(exp.equalsIgnoreCase("Invalid")){
				
				if(targetPage== true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
			
		}catch (Exception e) {
			Assert.fail();
		}
		logger.info("***** Finish TC003_LoginDDT ***** ");
	
	}
}
