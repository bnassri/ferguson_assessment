package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.MoenProductPage;
import com.build.qa.build.selenium.pageobjects.ShoppingCartPage;
import com.build.qa.build.selenium.pageobjects.MoenSearchResultsPage;
import com.build.qa.build.selenium.pageobjects.BathroomSinksPage;
import org.junit.Test;
import org.openqa.selenium.*;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

		MoenSearchResultsPage moenSearchResultsPage = new MoenSearchResultsPage(driver,wait);
		softly.assertThat(moenSearchResultsPage.onCorrectSearchResultsPage())
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

		softly.assertThat(bathroomSinksPage.onBathroomSinkFaucetPage())
				.as("Verify that user is on bathroom sink faucet page")
				.isTrue();

		String sinkPagePartNumber = bathroomSinksPage.findPartNumber("Pfister Pfirst Series™ Single Handle Monoblock Bathroom Sink Faucet in Polished Chrome");
		bathroomSinksPage.addToCartButton.click();


		//switch to popup for adding to cart, get part#, and add to cart
		driver.switchTo().activeElement().isDisplayed();
		wait.until(ExpectedConditions.elementToBeClickable(bathroomSinksPage.cartPopUpAddButton));
		bathroomSinksPage.cartPopUpAddButton.click();


		wait.until(ExpectedConditions.elementToBeClickable(bathroomSinksPage.shoppingCartButton));
		bathroomSinksPage.shoppingCartButton.click();


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

		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homepage = new HomePage(driver, wait);
		homepage.searchBox.sendKeys("Moen m6702bn");
		homepage.searchButton.click();

		MoenSearchResultsPage task1SearchResultsPage = new MoenSearchResultsPage(driver,wait);
		softly.assertThat(task1SearchResultsPage.onCorrectSearchResultsPage())
				.isTrue();

		MoenProductPage moenProductPage = new MoenProductPage(driver, wait);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver,wait);

		//get item number for brushed nickel finish and add to cart
		moenProductPage.brushedNickleFinish.click();
		String brushedNickelItemNo = moenProductPage.ItemNum.getText().substring(10);
		moenProductPage.addToCartButton.click();

		wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS);

		//get item number for matte black finsh and add to cart
		moenProductPage.matteBlackFinish.click();
		String matteBlackItemNo = moenProductPage.ItemNum.getText().substring(10);
		moenProductPage.addToCartButton.click();


		wait = new FluentWait<WebDriver>(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS);
		moenProductPage.shoppingCartButton.click();


		softly.assertThat(shoppingCartPage.onShoppingCartPage())
				.as("should be on shopping cart page")
				.isTrue();

		//get single item cost for both finishes
		String intialBNPrice = shoppingCartPage.priceBrushedNickel.getText();
		String intialMatteBlackPrice = shoppingCartPage.priceMatteBlack.getText();
		String totalPrice = shoppingCartPage.finalPrice.getText();

		//add 2 brushed nickel finish faucets
		shoppingCartPage.ItemLocator(brushedNickelItemNo).click();
		shoppingCartPage.ItemLocator(brushedNickelItemNo).clear();
		shoppingCartPage.ItemLocator(brushedNickelItemNo).sendKeys("2");

		//add 3 matte black finish faucets
		shoppingCartPage.ItemLocator(matteBlackItemNo).click();
		shoppingCartPage.ItemLocator(matteBlackItemNo).clear();
		shoppingCartPage.ItemLocator(matteBlackItemNo).sendKeys("3");

		Thread.sleep(3000);

		//get updated cost info
		String finalBNPrice = shoppingCartPage.priceBrushedNickel.getText();
		String finalMatteBlackPrice = shoppingCartPage.priceMatteBlack.getText();
		String finalPrice = shoppingCartPage.finalPrice.getText();

		softly.assertThat(!intialBNPrice.equals(finalBNPrice) && !intialMatteBlackPrice.equals(finalMatteBlackPrice)
		&& !totalPrice.equals(finalPrice)).isTrue();



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
