package com.baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LibraryClass {
    public static WebDriver driver;
    public static Properties prop;

    public void launchApplication() throws IOException {
        // 1. Read the Config File
        FileInputStream file = new FileInputStream("src/test/resources/Config/config.properties");
        prop = new Properties();
        prop.load(file);

        // 2. Determine which browser to open
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            // Selenium 4 automatically handles the driver setup!
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        // 3. Setup Browser Window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 4. Open the URL
        driver.get(prop.getProperty("url"));
    }
    
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
