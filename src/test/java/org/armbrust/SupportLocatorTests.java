package org.armbrust;

import org.armbrust.components.SelectMenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SupportLocatorTests {
    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    public void setUpTest() throws InterruptedException {
        // Set the driver path
        System.setProperty("webdriver.gecko.driver", "C:\\QA-Tools\\drivers\\geckodriver.exe");

        // Open the website
        driver.get("http://karinarmbrust.com/testdemo.html");
        Thread.sleep(3000);

        // Wait until Select Menu is ready
        SelectMenuComponent singleSelectMenu = new SelectMenuComponent(driver, By.id("elements"));
        singleSelectMenu.elementNowReady();

        // Select the option
        singleSelectMenu.selectSingleItemByValueByVisibleText("Support Locators");

    }

    // Uses the Support Locator ByIdOrName
    @Test(priority = 1)
    public void ByIdOrNameTest() throws InterruptedException {

        // Get the buttons initially
        WebElement button1 = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("buttonClick1")));
        Assert.assertEquals("Button Click 1", button1.getText());

        WebElement button2 = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("buttonClick2")));
        Assert.assertEquals("Button Click 2", button2.getText());

        WebElement button3 = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("buttonClick3A")));
        Assert.assertEquals("Button Click 3", button3.getText());

        // Get the first button with an Id or Name that is equal to a value
        WebElement buttonIdOrName1 = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(new ByIdOrName("buttonClick1")));
        System.out.println("Support Locators - ByIdOrName Test");
        System.out.println("First Test:");
        System.out.println("Expected: " + button1.getText());
        System.out.println("Actual: " + buttonIdOrName1.getText());
        Assert.assertEquals(button1, buttonIdOrName1);

        // Get the second button with an Id or Name that is equal to a value
        WebElement buttonIdOrName2 = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(new ByIdOrName("buttonClick2")));
        System.out.println("Second Test:");
        System.out.println("Expected: " + button2.getText());
        System.out.println("Actual: " + buttonIdOrName2.getText());
        Assert.assertEquals(button2, buttonIdOrName2);

        // Testing to ensure that ByIdOrName will get the name
        WebElement buttonIdOrName3 = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(new ByIdOrName("buttonClick3")));
        System.out.println("Third Test:");
        System.out.println("Expected: " + button3.getText());
        System.out.println("Actual: " + buttonIdOrName3.getText());
        Assert.assertEquals(button3, buttonIdOrName3);
    }

    // Uses the Support Locator ByAll
    @Test(priority = 2)
    public void ByAllTest() throws InterruptedException {
        final List<WebElement> allListItems = driver.findElements(new ByAll(By.className("list1item1")));
        System.out.println("Support Locator ByAll Test:");
        System.out.println("Expected: 6");
        System.out.println("Actual: " + allListItems.size());
        Assert.assertEquals(6, allListItems.size());
    }

    // Uses the Support Locator ByChained with 2 levels
    @Test(priority=3)
    public void ByChainedTestAll() throws InterruptedException {
        final List<WebElement> allListItemsChained = driver.findElements(new ByChained(By.id("supportLocatorList"),
                By.className("list1item1")));
        System.out.println("Support Locator ByChained Test:");
        System.out.println("Expected: 6");
        System.out.println("Actual: " + allListItemsChained.size());
        Assert.assertEquals(6, allListItemsChained.size());
    }

    // Uses the Support Locator ByChained with 3 levels
    @Test(priority=4)
    public void ByChainedTestAll2() throws InterruptedException {
        final List<WebElement> allListItemsChained = driver.findElements(new ByChained(By.id("supportLocatorList"),
                By.id("firstList"), By.className("list1item1")));
        System.out.println("Support Locator ByChained Test:");
        System.out.println("Expected: 4");
        System.out.println("Actual: " + allListItemsChained.size());
        Assert.assertEquals(4, allListItemsChained.size());
    }

    @AfterTest
    public void closeTest() throws InterruptedException {
        driver.quit();
    }
}
