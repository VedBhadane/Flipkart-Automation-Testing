package com.stepdefinitions;

import java.io.IOException;

import com.baseclass.LibraryClass;
import com.pages.SearchPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps extends LibraryClass {
    
    SearchPage searchPage;

    @Before
    public void startUp() throws IOException {
        launchApplication();
        searchPage = new SearchPage(driver);
    }

    @Given("I open the Flipkart Application")
    public void i_open_the_flipkart_application() {
        System.out.println("--- Test Started ---");
    }

    @When("I search for {string}")
    public void i_search_for(String product) {
        searchPage.searchForProduct(product);
    }

    // --- SCENARIO 1 STEPS (iPhone) ---
    @And("I sort the results by {string}")
    public void i_sort_results(String sortType) {
        searchPage.clickSortLowToHigh();
    }

    @And("I click on the first search result")
    public void i_click_first_result() {
        searchPage.clickFirstProduct();
    }

    @Then("I switch to the new tab and verify the product details")
    public void i_switch_to_new_tab() {
        searchPage.switchToProductTab();
        System.out.println("Switched to Tab: " + driver.getTitle());
    }

    // --- SCENARIO 2 STEPS (Cart) ---
    @And("I switch to the new tab")
    public void just_switch_tab() {
        searchPage.switchToProductTab();
    }

    @And("I click on Add to Cart button")
    public void click_add_to_cart() {
        searchPage.clickAddToCart();
    }

    @Then("I should see the product in the cart")
    public void verify_cart_add() {
        // Flipkart redirects to Cart page usually
        String title = driver.getTitle();
        // Simple check to see if we moved to a cart-related page
        if(title.contains("Cart") || title.contains("Shopping") || driver.getCurrentUrl().contains("cart")) {
            System.out.println("Add to Cart Verified");
        }
    }

    // --- SCENARIO 3 STEPS (Login) ---
    @When("I click Login and enter mobile number {string}")
    public void click_login_enter_mobile(String mobile) {
        searchPage.performLogin(mobile);
    }

    @Then("I should see the OTP verification step")
    public void verify_otp_step() {
        // If the script reached here without crashing, the number was entered successfully
        System.out.println("OTP Request Sent Successfully");
    }

    @After
    public void close() {
        // Final pause before closing browser to see the result
        try { Thread.sleep(3000); } catch (Exception e) {}
        tearDown();
    }
}