package com.example.apple.appiumtesting;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Created by Apple on 3/6/2018.
 */

public class AppiumTest {
    private AndroidDriver driver;
    String appName;

    @Factory(dataProvider = "parallelDp")
    public AppiumTest(String applicationName) {
        this.appName = applicationName;
    }

    @DataProvider(name = "parallelDp")
    public Object[][] parallelDp() {
        return new Object[][] {
                {"Moto E3"},
                {"Moto g3"}
        };
    }

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ANDROID");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        desiredCapabilities.setCapability("applicationName", this.appName);
        desiredCapabilities.setCapability("appPackage", "com.hypersoft.vwallet");
        desiredCapabilities.setCapability("appActivity", "com.hypersoft.vwallet.ui.activity.SignInActivity_");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4722/wd/hub"), desiredCapabilities);
    }

    @Test
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

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
