package com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.baseclass.LibraryClass;
import com.reusablefunctions.SeleniumUtility;

public class SearchPage extends LibraryClass {
    
    SeleniumUtility seleniumUtil;

    // --- 1. SEARCH & SORT LOCATORS ---
    @FindBy(name = "q") WebElement searchBox;
    @FindBy(xpath = "//div[contains(text(),'Price -- Low to High')]") WebElement sortLowToHigh;
    @FindBy(xpath = "(//div[@data-id])[1]//a") WebElement firstProductLink;

    // --- 2. ADD TO CART LOCATORS ---
    @FindBy(xpath = "//button[text()='Add to cart'] | //button[contains(.,'Add to cart')]") 
    WebElement addToCartButton;

    // --- 3. LOGIN LOCATORS ---
    @FindBy(xpath = "//a[contains(text(),'Login')] | //span[contains(text(),'Login')]") 
    WebElement loginButton;
    
    // --- THE FIX IS HERE ---
    // This locator says: Find the 'Request OTP' button, then find the Input field directly above it.
    // This makes it impossible to type in the search bar.
    @FindBy(xpath = "//button[contains(text(),'Request OTP')]//preceding::input[1]") 
    WebElement loginInput;
    
    @FindBy(xpath = "//button[contains(text(),'Request OTP')]") 
    WebElement requestOtpButton;
    
    // POPUP CLOSER
    @FindBy(xpath = "//button[contains(text(),'✕')] | //span[contains(text(),'✕')]")
    WebElement closePopupButton;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumUtil = new SeleniumUtility(driver);
    }

    // --- HELPER TO HANDLE POPUPS ---
    public void handlePopup() {
        try {
            Thread.sleep(1000); 
            if(closePopupButton.isDisplayed()) {
                closePopupButton.click();
                System.out.println("Popup Closed Successfully");
            }
        } catch (Exception e) {
            // No popup appeared, continue normal execution
        }
    }

    // --- ACTIONS ---

    public void searchForProduct(String productName) {
        handlePopup(); 
        seleniumUtil.enterText(searchBox, productName);
        seleniumUtil.pause(1); 
        searchBox.sendKeys(Keys.ENTER); 
    }
    
    public void clickSortLowToHigh() {
        seleniumUtil.pause(2);
        seleniumUtil.clickElement(sortLowToHigh);
    }
    
    public void clickFirstProduct() {
        seleniumUtil.pause(2);
        seleniumUtil.clickElement(firstProductLink);
    }
    
    public void switchToProductTab() {
        seleniumUtil.switchToNewWindow();
        seleniumUtil.pause(2); 
    }
    
    public void clickAddToCart() {
        seleniumUtil.pause(2);
        seleniumUtil.clickElement(addToCartButton);
    }
    
    public void performLogin(String phone) {
        // 1. Clear any accidental popups
        handlePopup(); 
        
        // 2. Click the main Login Button
        try {
            if(loginButton.isDisplayed()) {
                seleniumUtil.clickElement(loginButton);
            }
        } catch(Exception e) {
            System.out.println("Login button might already be clicked.");
        }
        
        seleniumUtil.pause(2); // Wait for Modal to slide in
        
        // 3. ENTER TEXT
        // The locator now forces it to look inside the modal
        seleniumUtil.enterText(loginInput, phone);
        
        seleniumUtil.pause(1);
        seleniumUtil.clickElement(requestOtpButton);
    }
}