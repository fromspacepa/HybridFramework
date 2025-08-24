package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement btn1Continue;

    public void setFirstName(String fname){
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname){
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String tel){
        txtTelephone.sendKeys(tel);
    }

    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String cpwd){
        txtConfirmPassword.sendKeys(cpwd);
    }

    public void setPrivacyPolicy(){
        chkdPolicy.click();
    }

    public void clickContinue(){
//Solution1
        btnContinue.click();
/*
//Solution2:
        btnContinue.submit();
//Solution3:
        Actions act = new Actions(driver);
        act.moveToElement(btnContinue).click().perform();
//Solution4:
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executorScript("arguments[0].click();", btnContinue);
//Solution5:
        btnContinue.sendKeys(Keys.RETURN);
//Solution6:
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
*/
    }

    public String getConfirmationMsg(){
        try{
            return (msgConfirmation.getText());
        } catch (Exception e){
            return (e.getMessage());
        }
    }

    public void setBtn1Continue() {
        btn1Continue.click();
    }
}
