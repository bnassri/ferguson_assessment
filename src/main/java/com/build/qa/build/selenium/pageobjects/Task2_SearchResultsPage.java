package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class Task2_SearchResultsPage extends BasePage {

    private By addToCart;
    private By shoppingCart;
    private By cartPopUp;
    private By cartAddConfirmation;


    public Task2_SearchResultsPage(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);


    }
}
