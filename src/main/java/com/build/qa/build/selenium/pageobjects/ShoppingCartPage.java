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

    public WebElement ItemLocator(String itemNumber){

        WebElement qtyBox = driver.findElement(By.xpath("(//*[@data-sku-id=\""+itemNumber+"\"])[2]"));
        return qtyBox;
    }

    @FindBy (css = "div.cl-info")
    public WebElement cartPartNumber;

    @FindBy (xpath = "((//*[@class=\"total-price\"])[1]//span[1])[1]")
    public WebElement priceBrushedNickel ;

    @FindBy (xpath = "((//*[@class=\"total-price\"])[2]//span[1])[1]")
    public WebElement priceMatteBlack;

    @FindBy (xpath = "//*[@class=\"os-shipping\"]//span[2]")
    public WebElement finalPrice;









}
