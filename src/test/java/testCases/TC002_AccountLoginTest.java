package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass{

	@Test(groups = {"Sanity","Master"})
	public void verify_login() {

		logger.info("***** Starting TC002 Login Test \"*****");

		try {
		// Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		// Login Page
		AccountLoginPage lp = new AccountLoginPage(driver);
		lp.setEmail(p.getProperty("oriemail"));
		lp.setPassword(p.getProperty("oripassword"));
		lp.clickbtnLogin();
		
		// My Account
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true,"Login Failed!..");
		}
		catch (Exception e) {
			Assert.fail();
		}
		logger.info("***** Finish TC002 Login Test \"*****");

	}
	
}
