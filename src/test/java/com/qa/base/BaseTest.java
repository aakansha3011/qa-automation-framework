package com.qa.base;
import com.aventstack.extentreports.ExtentReports;
import com.qa.utils.ExtentManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public static WebDriver driver;
    public static ExtentReports extent;

    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        extent = ExtentManager.getInstance();
        // existing code
    }
    

    public void tearDown() {
        driver.quit();
    }
}