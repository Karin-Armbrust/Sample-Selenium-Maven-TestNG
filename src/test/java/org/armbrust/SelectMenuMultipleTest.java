package org.armbrust;

import org.armbrust.components.SelectMenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SelectMenuMultipleTest {
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

    // Is the Select set to multiple Test
    @Test(priority = 1)
    public void SelectMenuIsMultipleTest() {

        // Wait until Select Menu is ready
        SelectMenuComponent multiSelectMenu = new SelectMenuComponent(driver, By.id("selectmenu1"));
        multiSelectMenu.elementNowReady();

        // Check to see if the Menu is a Multiple Select menu

        System.out.println("SelectMenuIsMultipleTest()");
        System.out.println("Expected: " + true);
        System.out.println("Actual:   " + multiSelectMenu.isMultipleSelect());

        Assert.assertTrue(multiSelectMenu.isMultipleSelect());
    }

    // How many options does the Select have Test
    @Test(priority = 2)
    public void SelectMenuOptionNumberTest() {

        // Wait until Select Menu is ready
        SelectMenuComponent multiSelectMenu = new SelectMenuComponent(driver, By.id("selectmenu1"));
        multiSelectMenu.elementNowReady();

        // Get the number of options
        int options = multiSelectMenu.getNumberOfOptions();

        // Check the number of options
        System.out.println("SelectMenuOptionNumberTest()");
        System.out.println("Expected: " + "5");
        System.out.println("Actual:   " + options);

        Assert.assertEquals(5, options);
    }

    // Select multiple values
    @Test(priority=3)
    public void SelectMultipleOptions() {

        // Wait until Select Menu is ready
        SelectMenuComponent multiSelectMenu = new SelectMenuComponent(driver, By.id("selectmenu1"));
        multiSelectMenu.elementNowReady();

        multiSelectMenu.selectSingleItemByValue("Red");
        multiSelectMenu.selectSingleItemByValue("Yellow");

        List<WebElement> selectedOptionList = multiSelectMenu.getMultiSelections();

        Assert.assertEquals(2, selectedOptionList.size());
        Assert.assertEquals("Red", selectedOptionList.get(0).getText());
        Assert.assertEquals("Yellow", selectedOptionList.get(1).getText());

        WebElement outputMessage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("selectMenuOutput")));

        // Test the output
        System.out.println("SelectMultipleOptions()");
        System.out.println("Expected: " + "You selected some stuff: Red Yellow");
        System.out.println("Actual:   " + outputMessage.getText());

        Assert.assertEquals("You selected some stuff: Red Yellow", outputMessage.getText());
    }

    // Select and deselect multiple values
    @Test(priority=4)
    public void DeselectMultipleOptions() {
        // Wait until Select Menu is ready
        SelectMenuComponent multiSelectMenu = new SelectMenuComponent(driver, By.id("selectmenu1"));
        multiSelectMenu.elementNowReady();

        // Set a couple of selections
        multiSelectMenu.selectSingleItemByValue("Red");
        multiSelectMenu.selectSingleItemByValue("Yellow");

        // deselect all the selections
        multiSelectMenu.deselectAll();

        // Check that there are no selections
        List<WebElement> selectedOptionList = multiSelectMenu.getMultiSelections();

        System.out.println("DeselectMultipleOptions()");
        System.out.println("Expected: " + "0");
        System.out.println("Actual:   " + selectedOptionList.size());

        Assert.assertEquals(0, selectedOptionList.size());
    }

    // Close the driver after each test
    @AfterTest
    public void CloseTest() {
        driver.quit();
    }
}

