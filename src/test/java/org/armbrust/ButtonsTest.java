package org.armbrust;

import org.armbrust.components.ButtonComponent;
import org.armbrust.components.SelectMenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

// These tests use the TestNG @BeforeTest/@AfterTest which sets up and breaks down the whole suite
// This is different than JUnit. Weird, I know...
public class ButtonsTest {
    // Initialize the Webdriver
    final WebDriver driver = new FirefoxDriver();


    // Set up each test
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
        singleSelectMenu.selectSingleItemByValueByVisibleText("Buttons");


    }

    // Sychronizing test - each button activates the next one
    @Test(priority = 4)
    public void ButtonSynchonizingTest() {
        // Click Button One
        ButtonComponent buttonOne = new ButtonComponent(driver, By.id("buttonOne"));
        buttonOne.waitTilReady();
        buttonOne.click();

        // Wait for Button Two and then click it
        ButtonComponent buttonTwo = new ButtonComponent(driver, By.id("buttonTwo"));
        buttonTwo.waitTilReady();
        buttonTwo.click();

        // Wait for Button Three and then click it
        ButtonComponent buttonThree = new ButtonComponent(driver, By.id("buttonThree"));
        buttonThree.waitTilReady();
        buttonThree.click();

        // Wait for Button Four and then click it
        ButtonComponent buttonFour = new ButtonComponent(driver, By.id("buttonFour"));
        buttonFour.waitTilReady();
        buttonFour.click();

        //Test the message
        // Wait until the the success message comes up
        WebElement successMessage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("buttonOutput2")));

        System.out.println("ButtonSynchonizingTest()");
        System.out.println("Expected Output: ");
        System.out.println("Actual Output: " + successMessage.getText());
        System.out.println("ButtonTwo Enabled Expected: false");
        System.out.println("ButtonTwo Enabled Actual: " + buttonTwo.isEnabled());
        System.out.println("ButtonThree Enabled Expected: false");
        System.out.println("ButtonThree Enabled Actual: " + buttonThree.isEnabled());
        System.out.println("ButtonFour Enabled Expected: false");
        System.out.println("ButtonFour Enabled Actual: " + buttonFour.isEnabled());

        assertEquals(successMessage.getText(), "");
        assertFalse(buttonTwo.isEnabled());
        assertFalse(buttonThree.isEnabled());
        assertFalse(buttonFour.isEnabled());


    }
    // Double Click Test
    @Test(priority = 1)
    public void ButtonDoubleClickTest() {

        // Wait until the element is present and ready
        ButtonComponent doubleClickMeButton = new ButtonComponent(driver,By.id("buttonDoubleClick"));
        doubleClickMeButton.elementNowReady();
        // When ready, double click the button
        doubleClickMeButton.doubleClick();

        // Wait until the the success message comes up
        WebElement successMessage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("buttonOutput")));

        // Test the message
        System.out.println("ButtonDoubleClickTest()");
        System.out.println("Expected: You clicked the Double Click Button!");
        System.out.println("Actual: " + successMessage.getText());
        assertEquals(successMessage.getText(), "You double clicked the Double Click Button!");


    }

    // Right Click Test
    @Test(priority = 2)
    public void ButtonRightClickTest() {
        // Wait until the element is present and ready
        ButtonComponent rightClickMeButton = new ButtonComponent(driver,By.id("buttonRightClick"));
        rightClickMeButton.elementNowReady();
        // When ready, double click the button
        rightClickMeButton.rightClick();

        // Wait until we get the success message and get it
        WebElement successMessage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("buttonOutput")));

        //Test the message
        System.out.println("ButtonRightClickTest()");
        System.out.println("Expected: You right clicked the Right Click Button!");
        System.out.println("Actual: " + successMessage.getText());
        assertEquals(successMessage.getText(), "You right clicked the Right Click Button!");

    }

    // Simple Click Test
    @Test(priority = 3)
    public void ButtonClickMeTest() {

        // Wait until the element is present and ready
        ButtonComponent ClickMeButton = new ButtonComponent(driver,By.id("buttonClick"));
        ClickMeButton.elementNowReady();
        // When ready, double click the button
        ClickMeButton.click();

        // Wait until we get the success message and get it
        WebElement successMessage = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("buttonOutput")));

        //Test the message
        System.out.println("ButtonClickMeTest()");
        System.out.println("Expected: You clicked the Single Click Button!");
        System.out.println("Actual: " + successMessage.getText());
        assertEquals(successMessage.getText(), "You clicked the Single Click Button!");
    }

    // Close the driver after each test
    @AfterTest
    public void CloseTest() {
        driver.quit();
    }

}
