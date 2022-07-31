package com.build.qa.build.selenium.pageobjects;
import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BathroomSinksPage extends BasePage {

    private By bathroomSinksPageWrapper;
    private By popUpAlert;


    public BathroomSinksPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
        bathroomSinksPageWrapper = By.cssSelector("#wrapper.plp");
        //popUpAlert = By.xpath("/html/body/div[15]/div/div");

    }

    //public boolean addToCartPopUp(){
    //    return wait.until(ExpectedConditions.presenceOfElementLocated(popUpAlert)) !=null;
    //}
    public boolean onBathroomSinksPage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(bathroomSinksPageWrapper)) != null;
    }

    public static String productPartNumberFinder = "//p[text()='?']//ancestor::div[@class='sr-content-box']//p[contains(text(),'Part') and not(contains(text(),'Mfr'))]/following-sibling::span";

    public String findPartNumber(String productName){

        WebElement targetProductElement = driver.findElement(By.xpath(productPartNumberFinder.replace("?", productName)));
        return targetProductElement.getText();
    }


    @FindBy (xpath = "//p[@data-productid = ',7289400']")
    public WebElement addToCartButton;

    @FindBy (css = "div[class='cart']" )
    public WebElement shoppingCart;

    @FindBy (xpath = "//button[@class='button mainly jq-quick-view-add-to-cart']")
    public WebElement cartPopUpAddButton;

    @FindBy (css = "div#addToCartTips")
    public WebElement cartAddConfirmation;

    @FindBy (xpath = "/html/body/div[15]/div/div")
    public WebElement popUpBox;





}
