package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    By addToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");

    public void addProductToCart() {
        driver.findElement(addToCartBtn).click();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();

        // wait until cart page loads
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlContains("cart"));
    }
    By cartItem = By.className("cart_item");

    public boolean isProductDisplayedInCart() {
        return driver.findElements(cartItem).size() > 0;
    }
    
}