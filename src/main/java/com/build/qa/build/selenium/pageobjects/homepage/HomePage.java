package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {


	private By homePageWrapper;
	public By homePageSearch;
	
	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		homePageWrapper = By.cssSelector("#wrapper.homepage");

	}
	
	public boolean onHomePage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(homePageWrapper)) != null;
	}

	@FindBy(xpath = "//input[@class='text-input search react-search-input-normal js-reload-value']")
	public WebElement searchBox;

	@FindBy(xpath = "//a[@href='javascript:;']")
	public WebElement searchButton;


}
