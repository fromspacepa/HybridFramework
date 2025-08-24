package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {


    @Test(groups={"Regression","Master"})
    public void verify_account_registration() throws InterruptedException {

        logger.info("*** Starting the testCase: TC001_AccountRegistrationTest ***");

        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("*** Clicked on MyAccount Link ***");
            hp.clickRegister();
            logger.info("*** Clicked on Registration Link ***");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
            logger.info("*** Providing customer details ***");
            regpage.setFirstName(randomAlphabetic());
            regpage.setLastName(randomAlphabetic());
            regpage.setEmail(randomAlphabetic() + "@gmail.com");
            regpage.setTelephone(randomNumber());

            String password = randomAlphabetic();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.setPrivacyPolicy();
            regpage.clickContinue();


            logger.info("*** Validating expected message ***");
            String confmsg = regpage.getConfirmationMsg();

            regpage.setBtn1Continue();

           if(confmsg.equals("Your Account Has Been Created!"))
           {
            Assert.assertTrue(true);
           }else{
            logger.error("Test got FAILED!!!");
            logger.debug("Debug Logs....");
            Assert.assertTrue(false);
           }

        } catch (Exception e){
           Assert.fail();
        }

        logger.info("*** Finished the testCase: TC001_AccountRegistrationTest ***");



/*
HARD CODED:
regpage.setFirstName("Shah");
regpage.setLastName("Khan");
regpage.setEmail("shah.@gmail.com");
regpage.setTelephone("1234567881");
regpage.setPassword("248ba");
regpage.setConfirmPassword("248ba");
*/


    }



}
