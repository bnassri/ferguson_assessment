package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.MoenProductPage;
import com.build.qa.build.selenium.pageobjects.ShoppingCartPage;
import com.build.qa.build.selenium.pageobjects.Task1_SearchResultsPage;
import com.build.qa.build.selenium.pageobjects.BathroomSinksPage;
import org.junit.Test;
import org.openqa.selenium.*;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FergTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onHomePage())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * @assert that the product page shown/displayed is what is expected by checking the product brand and product id
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
		// Task #1: Implement this test
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homepage = new HomePage(driver, wait);
		homepage.searchBox.sendKeys("Moen m6702bn");
		homepage.searchButton.click();

		Task1_SearchResultsPage task1SearchResultsPage = new Task1_SearchResultsPage(driver,wait);
		softly.assertThat(task1SearchResultsPage.onCorrectSearchResultsPage())
				.as("The correct product brand and product id should be displayed")
				.isTrue();
	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert that the expected product is added to the cart
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {
		// Task #2: Implement this test
		driver.get(getConfiguration("SinkSearch"));
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver,wait);
		BathroomSinksPage bathroomSinksPage = new BathroomSinksPage(driver, wait);
		bathroomSinksPage.addToCartButton.click();



		driver.switchTo().activeElement().isDisplayed();
		wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
		String sinkPagePartNumber = bathroomSinksPage.findPartNumber("Pfister Pfirst Series™ Single Handle Monoblock Bathroom Sink Faucet in Polished Chrome");
		bathroomSinksPage.cartPopUpAddButton.click();


		wait = new FluentWait<WebDriver>(driver).withTimeout(2, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS);
		bathroomSinksPage.shoppingCart.click();

		softly.assertThat((shoppingCartPage.cartPartNumber.getText()).contains(sinkPagePartNumber))
				.as("part numbers must match")
				.isTrue();


	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * @assert that the product and cart total update as expected when the quantity is changed
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addMultipleCartItemsAndChangeQuantity() throws InterruptedException {
		// Task #3: Implement this test
	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() throws InterruptedException {
		// Task #4: Implement this test

		/*
		WebElements list
		for each - one by one and does each contain the filter keywords
		.fg-search-results-li
		 */

		driver.get(getConfiguration("SinkSearch"));
		BathroomSinksPage bathroomSinksPage = new BathroomSinksPage(driver, wait);

		//no filter
		int itemsBeforeFirstFilter = Integer.parseInt(bathroomSinksPage.itemsBeforeFiltering.getAttribute("data-total-record"));


		//filter by Moen products
		bathroomSinksPage.moenCheckBox.click();
		Thread.sleep(3000);
		int itemsAfterMoenFilter = Integer.parseInt(bathroomSinksPage.itemCountAfterMoenFilter.getAttribute("data-total-record"));


		//verify that Moen filter returns less than no filters
		softly.assertThat(itemsAfterMoenFilter < itemsBeforeFirstFilter)
				.as("item count after Moen filter should be less than item count before any filters")
				.isTrue();


		//filter by Moen & monoblock products
		bathroomSinksPage.monoblockCheckBox.click();
		Thread.sleep(3000);
		int itemsAfterMonoblockFilter = Integer.parseInt(bathroomSinksPage.itemCountAfterBothFilters.getAttribute("data-total-record"));


		//verify that monoblock + Moen filter returns less than just Moen filter
		softly.assertThat(itemsAfterMonoblockFilter < itemsAfterMoenFilter)
				.as("item count after Moen filter should be less than item count before any filters")
				.isTrue();

		bathroomSinksPage.showMoreButton.click();
		Thread.sleep(3000);


		List<WebElement> allItems = driver.findElements(By.cssSelector(".fg-search-results-li"));
		int totalItemsAfterFilter = allItems.size();

		//verify that the two filter search result contains both Moen and Monoblock
		for(WebElement element: allItems){
			softly.assertThat(element.findElement(By.xpath("//a[@title = *]"))
					.getText().toLowerCase().contains("moen"))
					.as("element must contains 'moen'")
					.isTrue();
			softly.assertThat(element.findElement(By.xpath("//a[@title = *]"))
							.getText().toLowerCase().contains("monoblock"))
					.as("element must contains 'monoblock'")
					.isTrue();
		}


		//verify that result count matches count after both filters
		softly.assertThat(totalItemsAfterFilter == itemsAfterMonoblockFilter)
				.as("number of items in list should equal number of items after both filters applied")
				.isTrue();




	}

}
