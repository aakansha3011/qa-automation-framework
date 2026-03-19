package com.qa.tests;

import org.testng.Assert;
import com.qa.pages.InventoryPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;

import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;

public class LoginTest extends BaseTest {

	    LoginPage loginPage;

	    @BeforeMethod
	    public void setUp() {
	        setup();
	        loginPage = new LoginPage(driver);
	    }

	    @Test
	    public void validLoginTest() {
	        ExtentTest test = extent.createTest("Valid Login Test");

	        loginPage.login("standard_user", "secret_sauce");

	        String url = driver.getCurrentUrl();

	        try {
	            Assert.assertTrue(url.contains("inventory"));
	            test.pass("Login successful");
	        } catch (AssertionError e) {
	            test.fail("Login failed");
	            throw e;
	        }
	    }

	    @Test
	    public void invalidLoginTest() {
	        ExtentTest test = extent.createTest("Invalid Login Test");

	        loginPage.login("wrong_user", "wrong_pass");

	        String error = loginPage.getErrorMessage();

	        try {
	            Assert.assertTrue(error.contains("Username and password"));
	            test.pass("Error message validated");
	        } catch (AssertionError e) {
	            test.fail("Error message not displayed correctly");
	            throw e;
	        }
	    }

	    @Test
	    public void addToCartTest() {
	        ExtentTest test = extent.createTest("Add To Cart Test");

	        loginPage.login("standard_user", "secret_sauce");

	        InventoryPage inventoryPage = new InventoryPage(driver);

	        inventoryPage.addProductToCart();
	        inventoryPage.clickCart();

	        String currentUrl = driver.getCurrentUrl();
	        System.out.println("Current URL: " + currentUrl);

	        try {
	            Assert.assertTrue(currentUrl.contains("cart"));
	            test.pass("Product successfully added to cart");
	        } catch (AssertionError e) {
	            test.fail("Product not added to cart");
	            throw e;
	        }
	    }

	    @AfterSuite
	    public void generateReport() {
	        extent.flush();
	    }

	    @AfterMethod
	    public void tearDownTest() {
	        tearDown();
	    }
	}