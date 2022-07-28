package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.build.qa.build.selenium.pageobjects.BasePage;

public class SearchResultsPage extends BasePage {
    private By brandName;
    private By productNum;

    public SearchResultsPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
        brandName = By.xpath("//h2[@class='product__brand']") ;
        productNum = By.xpath("//span[. = 'Part #M6702BN']");
    }

    public Boolean onCorrectSearchResultsPage(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(brandName)) !=null &&
                wait.until(ExpectedConditions.presenceOfElementLocated(productNum)) !=null;

    }
}
