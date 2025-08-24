package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {


    @Test(groups={"Sanity","Master"})//Here adding the property"groups" as parameter of the "@Test" annotation is grouping this testCase with other Tests.
    public void verify_login(){

        logger.info("*** Start Testing the: TC002_LoginTest ***");
        try
        {
            //HomePage:
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //LoginPage:
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));//Here the parameter "email" is loaded from the "config.properties" file, and should be used a String, because it does not belong to Java.
            lp.setPassword(p.getProperty("password"));//Here the parameter "password" is loaded from the "config.properties" file, and should be used a String, because it does not belong to Java.
            lp.clickLogin();

            //MyAccount Page:
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();
            //Assert.assertEquals(targetPage, true, "Login FAILED!");//This option can be used also if preferred.
            Assert.assertTrue(targetPage);
        }catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("*** Finished Testing the: TC002_LoginTest ***");

    }

}
