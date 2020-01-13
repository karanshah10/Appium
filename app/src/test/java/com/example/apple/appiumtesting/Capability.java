package com.example.apple.appiumtesting;

/**
 * Created by Apple on 3/6/2018.
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Created by Apple-pc on 01-03-2018.
 */

public class Capability {
    //  static  public ExtentReports extent;
//  static  public ExtentTest logger;
    public static AndroidDriver<MobileElement> driver;

    @Parameters({"deviceName_", "platformVersion_", "URL_"})
    @BeforeClass
    public void setUp(String deviceName_, String platformVersion_, String URL_) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName_);
        capabilities.setCapability(MobileCapabilityType.UDID,deviceName_);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
        capabilities.setCapability(CapabilityType.VERSION, platformVersion_);
        capabilities.setCapability("platformName", "Android");
//      capabilities.setCapability("appPackage", "com.edgeof.event");
//      capabilities.setCapability("appActivity", "com.edgeof.app.ui.activity.EventListActivity");
        capabilities.setCapability("appPackage", "com.edgeof.member");
        capabilities.setCapability("appActivity", "com.edgeof.app.ui.activity.SplashScreenActivity");
//        driver = new AndroidDriver<>(new URL("http://192.168.0.196:4723/wd/hub"), capabilities);
        driver = new AndroidDriver<>(new URL("http://" + URL_), capabilities);
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(500);

    }
  /*  @BeforeTest
    public void beforeTest(){
        //ExtentReports(String filePath,Boolean replaceExisting)
        //filepath - path of the file, in .htm or .html format - path where your report needs to generate.
        //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
        //True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
        //False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
        extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/EOSMemberApp_01_03.html", false);//System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true
        //extent.addSystemInfo("Environment","Environment Name")
        extent
                .addSystemInfo("Host Name", "SoftwareTestingMaterial")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Apple");
        //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
    @AfterMethod
    public void getResult(ITestResult result) throws Exception{
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.â€‚â€‚
            String screenshotPath = Capability.getScreenshot(driver, result.getName());
            //To add it in the extent report
//            logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
            InputStream is = new FileInputStream(screenshotPath);
            byte[] imageBytes = IOUtils.toByteArray(is);
            Thread.sleep(2000);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            String imgFormat = "data:image/png;base64," + base64;
            logger.log(LogStatus.PASS, logger.addScreenCapture(imgFormat));
        }else if(result.getStatus() == ITestResult.SKIP){
            logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
            String screenshotPath = Capability.getScreenshot(driver, result.getName());
            //To add it in the extent report
//            logger.log(LogStatus.SKIP, logger.addScreenCapture(screenshotPath));
            InputStream is = new FileInputStream(screenshotPath);
            byte[] imageBytes = IOUtils.toByteArray(is);
            Thread.sleep(2000);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            String imgFormat = "data:image/png;base64," + base64;
            logger.log(LogStatus.PASS, logger.addScreenCapture(imgFormat));
        }
        else
        {
            logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
            String screenshotPath = Capability.getScreenshot(driver, result.getName());
            //To add it in the extent report
//            logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
            InputStream is = new FileInputStream(screenshotPath);
            byte[] imageBytes = IOUtils.toByteArray(is);
            Thread.sleep(2000);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            String imgFormat = "data:image/png;base64," + base64;
            logger.log(LogStatus.PASS, logger.addScreenCapture(imgFormat));
        }
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        extent.endTest(logger);
    }

    @AfterTest

    public void endReport(){
        extent.flush();
        extent.close();
    }*/

    @AfterClass
    public void afterTest() {
        driver.quit();
    }

    @Test(priority = 0)
    public void UnlockedDevice_Test() {
//        logger = extent.startTest("UnlockedDevice_Test");
        if (((AndroidDriver) driver).isLocked()) {
            ((AndroidDriver) driver).unlockDevice();
        }

    }


    @Test(priority = 1)              ////To verify the login functionality with valid data
    public void LoginWithValidData() throws InterruptedException {
//        logger = extent.startTest("LoginWithValidData");
//        driver.resetApp();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.id("com.edgeof.member:id/et_email")).clear();
        driver.findElement(By.id("com.edgeof.member:id/et_email")).sendKeys("yogeen.loriya@peaas.co");
//        driver.findElement(By.id("com.edgeof.member:id/et_pwd")).clear();
        driver.findElement(By.id("com.edgeof.member:id/et_pwd")).sendKeys("123456");
        Thread.sleep(3000);
        driver.hideKeyboard();
        driver.findElement(By.id("com.edgeof.member:id/login_btn")).click();
        Thread.sleep(3000);
       /* WebElement e1 = driver.findElement(By.name("Unlock a door"));
        if (e1.isDisplayed()) {
            System.out.println("In dashboard page");
        }*/
    }
}
