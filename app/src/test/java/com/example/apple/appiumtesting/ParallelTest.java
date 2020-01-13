package com.example.apple.appiumtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Created by Apple on 3/6/2018.
 */

public class ParallelTest implements Runnable {
    String uuid;
    String url;

    AppiumDriver<WebElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    public ParallelTest(String uuid, String url) {
        this.uuid = uuid;
        this.url = url;
    }

    @BeforeMethod
    public static void main(String args[]) {
        Runnable r1 = new ParallelTest("HKE67M1C", "4722");
        Runnable r2 = new ParallelTest("", "4723");
        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Override
    public void run() {
        try {
            capabilities.setCapability("device name", "Mobile name");
            // capabilities.setCapability("uuid", uuid);
            capabilities.setCapability("PlateformVersion", "7.1.2");
            capabilities.setCapability("plateFormName", "Android");
            capabilities.setCapability("appPackage", "com.hypersoft.vwallet");
            capabilities.setCapability("appActivity", "com.hypersoft.vwallet.ui.activity.SignInActivity_");
            driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:" + url + "/wd/hub"), capabilities);
            Thread.sleep(1000);

            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testMultipleDevics() throws MalformedURLException, InterruptedException {

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
