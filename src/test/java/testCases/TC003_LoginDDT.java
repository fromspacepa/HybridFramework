package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 Positive Test:
 If LoginData is Valid -and- Login is Success -then- the Test is PASSED -then- ->Logout.
 If LoginData is Valid -but- Login is Failed -then- the Test is FAILED

 Negative Test:
 If LoginData is Invalid -and- Login is Success -then- the Test is FAILED -then- ->Logout.
 If LoginData is Invalid -and- Login is Failed -then- the Test is Success
 */

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider="LoginData", dataProviderClass= DataProviders.class, groups="DataDriven")// Getting dataProvider from a different class. (Here we provide value "LoginData" of "@DataProvider" annotation,
                                                                          // and the path of the DataProvider class). However, if the DataProvider was in the same class, then will not need to provide the 2nd parameter.
    public void verify_LoginDDT(String email, String pwd, String expectedResult){
        logger.info("*** Start Testing: TC003_LoingDDT ***");
        try {
            //HomePage:
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //LoginPage:
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);//Here the parameter "email" is loaded from the "config.properties" file, and should be used a String, because it does not belong to Java.
            lp.setPassword(pwd);//Here the parameter "password" is loaded from the "config.properties" file, and should be used a String, because it does not belong to Java.
            lp.clickLogin();

            //MyAccountPage:
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();
            //Assert.assertEquals(targetPage, true, "Login FAILED!");//This option can be used also if preferred.
            //Assert.assertTrue(targetPage);

            if (expectedResult.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (expectedResult.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail();
        }

        logger.info("*** Finished Testing: TC003_LoingDDT ***");
    }


}
