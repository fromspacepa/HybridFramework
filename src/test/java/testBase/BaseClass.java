package testBase;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import testCases.TC002_LoginTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

/*
This class (BaseClass) is the Parent of all TestClass, which contains the reusable methods, that are going to be used in all TestClass by extending this class:
The main idea of creating this class is to create methods that are common throughout all the TestClasses.

 */

public class BaseClass {

    public static WebDriver driver;
    public Logger logger; //Log4j
    public Properties p;
    @BeforeClass(groups = {"Sanity","Regression","Master"})
    @Parameters({"operatingSystem", "browser"})
    public void setup(String operatingSystem, String browser) throws IOException {

        //Loading config.properties file:
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        logger= LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase()){
            case "chrome" : driver = new ChromeDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox" : driver = new FirefoxDriver(); break;
            default: System.out.println("Invalid browser name..."); return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Loading the application URL from properties file:
        driver.get(p.getProperty("appURL2"));
        // driver.get(p.getProperty("appURL2");

        /*
        Hard coding to get the application URL:
        driver.get("https://tutorialsninja.com/demo/");
        driver.get("http://localhost/opencart/upload/");
         */

        driver.manage().window().maximize();
    }

    @AfterClass(groups={"Sanity","Regression","Master"})
    public void tearDown(){
        driver.quit();
   }



// Alphabetic (a-z, A-Z):
    public String randomAlphabetic() {
        String generatedString = RandomStringGenerator.builder().withinRange('a', 'z').build().generate(4, 6); // lowercase (charRange a-z, size 4-6 chars)
        // String generatedString2 = RandomStringGenerator.builder().withinRange('A', 'Z').build().generate(6); // UPPERCASE (charRange A-Z, size 6 chars)
        // String generatedString = RandomStringGenerator.builder().withinRange('a', 'z').build().generate(2,7); // lowercase (charRange a-z, size 2-7 chars)
        // return (generatedString + generateString2); // Return concatenation of lower & UPPERCASE.
        return generatedString;
    }
// Numeric (0-9):
    public String randomNumber() {
        String generatedNumber = RandomStringGenerator.builder().withinRange('0', '9').build().generate(10); // numberGenerating (range of 0-9, size 10 digits)
        // String generatedNumber2 = RandomStringGenerator.builder().withinRange('0', '9').build().generate(3, 12); // numberGenerating (range of 0-9, size 3-12 digits)
        // return generatedNumber;
        return ("+1" + generatedNumber);
    }
// AlphaNumeric (a-z, A-Z, 0-9):
    public String randomAlphaNumeric(){
        String generatedAlphaNumeric = RandomStringGenerator.builder().withinRange('0', 'z').filteredBy(Character::isLetterOrDigit).build().generate(8);
        return generatedAlphaNumeric;
    }
// ASCII Special Character :

/*
Solution1:
// Alphabetic (a-z, A-Z):
    public String randomAlphabetic1() {
        RandomStringGenerator alphabeticGen = new RandomStringGenerator.Builder().withinRange('a', 'z').build();//Lower-case
        RandomStringGenerator alphabeticGen = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();//Upper-case
        String generatedString = alphabeticGen.generate(5);
        String generatedString = alphabeticGen.generate(2, 7);
        return generatedString;
    }
// Numeric (0-9):
    public String randomNumber1() {
        RandomStringGenerator numberGen = new RandomStringGenerator.Builder().withinRange('0', '9').build();
        String generatedNumber = numberGen.generate(10);
        String generatedNumber = numberGen.generate(3, 12);
        return generatedNumber;
    }
// AlphaNumeric (a-z, A-z, 0-9):
    public String randomAlphaNumeric(){
        RandomStringGenerator alphaNumericGen = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(Character::isLetterOrDigit).build();
        String generatedAlphaNumeric = alphaNumericGen.generate(8);
        return generatedAlphaNumeric;
// DEPRECATED!!!
public String randomAlphabetic(){
    String generatedString = RandomStringUtils.randomAlphabetic(5);
    return generatedString;
}
public String randomNumber(){
    String generatedNumber = RandomStringUtils.randomNumeric(10);
    return generatedNumber;
}
public String randomeAlphaNumberic(){
    String generatedString = RandomStringUtils.randomAlphabetic(3);
    String generatedNumber = RandomStringUtils.randomNumeric(10);
    return (generatedString + "@" + generatedNumber);
}

 */

    public String captureScreen(String takeName) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + takeName + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }



}
