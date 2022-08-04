package com.build.qa.build.selenium.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BathroomSinksPage extends BasePage {

    private By bathroomSinksPageWrapper;


    public BathroomSinksPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
        bathroomSinksPageWrapper = By.cssSelector("#wrapper.plp");

    }

    public boolean onBathroomSinkFaucetPage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(bathroomSinksPageWrapper)) != null;
    }


    //method to find any part number on the page
    public static String productPartNumberFinder = "//p[text()='?']//ancestor::div[@class='sr-content-box']//p[contains(text(),'Part') and not(contains(text(),'Mfr'))]/following-sibling::span";

    public String findPartNumber(String productName){

        WebElement targetProductElement = driver.findElement(By.xpath(productPartNumberFinder.replace("?", productName)));
        return targetProductElement.getText();
    }


    @FindBy (xpath = "//p[@data-productid = ',7289400']")
    public WebElement addToCartButton;

    @FindBy (css = "div[class='cart']" )
    public WebElement shoppingCartButton;

    @FindBy (xpath = "//button[@class='button mainly jq-quick-view-add-to-cart']")
    public WebElement cartPopUpAddButton;


    @FindBy (xpath = "(//*[@class='fcheckbox'])[6]")
    public WebElement moenCheckBox;

    @FindBy (xpath = "(//*[@class='fcheckbox'])[14]")
    public WebElement monoblockCheckBox;

    @FindBy (xpath = "//*[. = 'Show More']")
    public WebElement showMoreButton;

    @FindBy (xpath = "//div[@data-total-record]")
    public WebElement itemsBeforeFiltering;

    @FindBy (xpath = "//div[@data-total-record]")
    public WebElement itemCountAfterMoenFilter;

    @FindBy (xpath = "//div[@data-total-record]")
    public  WebElement itemCountAfterBothFilters;



}
