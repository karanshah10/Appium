package com.example.apple.appiumtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Apple on 2/22/2018.
 */

public class FirstTest {
    private static AndroidDriver<MobileElement> driver;

    @Parameters({"deviceName_", "UDID_", "platformVersion_", "URL_"})
    @BeforeMethod
    public void beforeMethod(String deviceName_, String UDID_, String platformVersion_, String URL_) throws MalformedURLException, InterruptedException {
        //File classpathRoot = new File(System.getProperty("user.dir"));
        //File appDir = new File(classpathRoot, "/Apps/Amazon/");
        //File app = new File(appDir, "in.amazon.mShop.android.shopping.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName", deviceName_);
        capabilities.setCapability("udid", UDID_);
        capabilities.setCapability("platformVersion", platformVersion_);
        capabilities.setCapability("platformName", "Android");
        //capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.hypersoft.vwallet");
        capabilities.setCapability("appActivity", "com.hypersoft.vwallet.ui.activity.SignInActivity_");
        driver = new AndroidDriver<MobileElement>(new URL("http://" + URL_), capabilities);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        Thread.sleep(10000);
    }

    @org.testng.annotations.Test
    public void aRegistration_Test() {

        driver.findElement(By.id("com.hypersoft.vwallet:id/tvSignUp")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("com.hypersoft.vwallet:id/btnAgree")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.hypersoft.vwallet:id/etMailAddress")).sendKeys("vatsal.harde@peaas.co");
        driver.findElement(By.id("com.hypersoft.vwallet:id/etPassword")).sendKeys("vatsal244");
        driver.findElement(By.id("com.hypersoft.vwallet:id/etPasswordRe")).sendKeys("vatsal244");
        driver.findElement(By.id("com.hypersoft.vwallet:id/etLastName")).sendKeys("Harde");
        driver.findElement(By.id("com.hypersoft.vwallet:id/etFirstName")).sendKeys("Vatsal");
        driver.hideKeyboard();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("com.hypersoft.vwallet:id/btnSignUp")).click();
        driver.findElement(By.className("android.widget.ImageButton")).click();
        driver.findElementByName("Logout").click();
        driver.findElement(By.id("android:id/button1")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() {
    }
}

