package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {

    private By shoppingCartWrapper;

    public ShoppingCartPage(WebDriver driver, Wait<WebDriver> wait){
        super(driver,wait);
        shoppingCartWrapper = By.cssSelector("#wrapper.cart");
    }
    public boolean onShoppingCartPage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(shoppingCartWrapper)) != null;
    }

    @FindBy (css = "div.cl-info")
    public WebElement cartPartNumber;
}
