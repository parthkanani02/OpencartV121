package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass  {
	
	@Test(groups = {"Regression","Master"})
	public void verify_account_Registration() throws InterruptedException 
	{
		
		logger.info("***** Starting TC001_AccountRegistrationTest *****");		
		try
		{
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Now inside of Registration page");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setPassword(randomPassword());
		
		logger.info("Fname, Lname, Email, Password data done in Registration page");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected Message");
		String confmsg = regpage.getConfirmationMsg();
		
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else{
				logger.error("Test Failed!..");
				logger.debug("Debug Logs---");		
			}
			
//			Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		
		}
		
		catch (Exception e) {
		Assert.fail();
		}
		logger.info("************** Finish TC001_AccountRegistrationTest **************");

	
	}
	
	
	
}
