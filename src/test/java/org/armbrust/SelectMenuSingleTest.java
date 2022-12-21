package org.armbrust;

import org.armbrust.components.SelectMenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SelectMenuSingleTest {
    // Initialize the Webdriver
    final WebDriver driver = new FirefoxDriver();

    // Set up before the tests are run Note: This is different than JUnit in that
    // @BeforeTest is run once before all the tests
    // and @AfterTest Are run once after all the tests are complete.
    @BeforeTest
    public void SetUpTest() throws InterruptedException {
        // Set the driver path
        System.setProperty("webdriver.gecko.driver", "C:\\QA-Tools\\drivers\\geckodriver.exe");

        // Open the website
        driver.get("http://karinarmbrust.com/testdemo.html");
        Thread.sleep(3000);

        // Wait until Select Menu is ready
        SelectMenuComponent singleSelectMenu = new SelectMenuComponent(driver, By.id("elements"));
        singleSelectMenu.elementNowReady();

        // Select the option
        singleSelectMenu.selectSingleItemByValueByVisibleText("Select Menus");
    }

    // Tests that the Select menu is a Single
    @Test(priority=1)
    public void SelectMenuIsSingleTest() {

        // Wait until Select Menu is ready
        SelectMenuComponent singleSelectMenu = new SelectMenuComponent(driver, By.id("selectmenu2"));
        singleSelectMenu.elementNowReady();

        // Check to see if the Menu is a Multiple Select menu

        System.out.println("SelectMenuIsMultipleTest()");
        System.out.println("Expected: " + false);
        System.out.println("Actual:   " + singleSelectMenu.isMultipleSelect());

        Assert.assertFalse(singleSelectMenu.isMultipleSelect());
    }

    // Check the number of options
    @Test(priority=2)
    public void SelectMenuSingleOptionNumberTest() {
        // Wait until Select Menu is ready
        SelectMenuComponent singleSelectMenu = new SelectMenuComponent(driver, By.id("selectmenu2"));
        singleSelectMenu.elementNowReady();

        // Get the number of options
        int options = singleSelectMenu.getNumberOfOptions();

        // Ensure the result is 5

        System.out.println("SelectMenuSingleOptionNumberTest()");
        System.out.println("Expected: " + "5");
        System.out.println("Actual:   " + options);

        Assert.assertEquals(5, options);
    }

    // A Test that selects several values but expects only the last one to persist
    @Test(priority=3)
    public void SelectOneValue() {
        // Wait until Select Menu is ready
        SelectMenuComponent singleSelectMenu = new SelectMenuComponent(driver, By.id("selectmenu2"));
        singleSelectMenu.elementNowReady();

        // Select several options
        // Expecting that Bike will be deselected leaving only Train
        singleSelectMenu.selectSingleItemByValue("Bike");
        singleSelectMenu.selectSingleItemByValue("Train");

        WebElement outputMessage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("selectMenuOutput2")));

        // Ensure Train was selected


        System.out.println("SelectOneValue()");
        System.out.println("Expected: " + "You selected some stuff: Train");
        System.out.println("Actual:   " + outputMessage.getText());

        Assert.assertEquals("You selected some stuff: Train",
                outputMessage.getText());
    }

    // Close the driver after each test
    @AfterTest
    public void CloseTest() {
        driver.quit();
    }
}

