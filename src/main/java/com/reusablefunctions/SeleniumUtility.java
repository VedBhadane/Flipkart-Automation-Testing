package com.reusablefunctions;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.baseclass.LibraryClass;

public class SeleniumUtility extends LibraryClass {
    
    public SeleniumUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void enterText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }
    
    public String getText(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public void switchToNewWindow() {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for(String window : allWindows) {
            if(!window.equals(parentWindow)) {
                driver.switchTo().window(window);
            }
        }
    }
    
    // --- NEW: Hover over a menu (like 'Fashion') ---
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }
    
    // --- NEW: The 2-Second Pauser ---
    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}