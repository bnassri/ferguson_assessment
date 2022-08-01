package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MoenProductPage extends BasePage{

    private By productPageWrapper;

    public MoenProductPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
        productPageWrapper = By.cssSelector("#wrapper.productdetail-new");

    }

    public Boolean onProductPage(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(productPageWrapper)) != null;
    }

    /*
    public static String skuIDFinder = "//input[@data-sku-id = '?']";

    public String findSKU(Long productSKU){
        WebElement targetProductElement = driver.findElement(By.xpath(skuIDFinder.replace("?", productSKU)));
        return targetProductElement.getText();
    }

     */

    @FindBy (xpath = "//a[@rel='Brushed Nickel']")
    public WebElement brushedNickleFinish;

    @FindBy (xpath = "//a[@rel='Matte Black']")
    public WebElement matteBlackFinish;

    @FindBy (xpath = "//input[@value ='Add to Cart']")
    public WebElement addToCartButton;

    @FindBy (xpath = "//div[@class = 'success__message']")
    public WebElement addToCartSuccessMsg;

    @FindBy (xpath = "//a[@class= 'js-cartitem-count cartitem-count ']")
    public WebElement shoppingCartButton;

    @FindBy (xpath = "(//*[contains(text(),'Item #')])[1]")
    public WebElement ItemNum;



}
