package org.armbrust.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ButtonComponent extends WaitTillReady {
    private final By locator;
    private final WebDriver driver;

    // Constructor
    public ButtonComponent(WebDriver driver, final By locator) {
        super(driver, 10);
        this.driver = driver;
        this.locator = locator;
    }

    // Check that the button is ready and clickable
    public void elementNowReady() {
        WebElement element = this.driver.findElement(this.locator);

        if (element.isDisplayed() && element.isEnabled()) {
            return;
        } else {
            throw new IllegalStateException(String.format("The Button %s is not ready.", this.locator));
        }
    }

    // Click Button
    public void click() {
        this.driver.findElement(this.locator).click();
    }

    // Double Click Button
    public void doubleClick() {
        Actions action = new Actions(driver);
        action.doubleClick(this.driver.findElement(this.locator)).perform();
    }

    // Right Click Button
    public void rightClick() {
        Actions action = new Actions(driver);
        action.contextClick(this.driver.findElement(this.locator)).perform();
    }

    // Is button Displayed?
    public Boolean isDisplayed() {
        return this.driver.findElement(this.locator).isDisplayed();
    }

    // Is button Enabled?
    public Boolean isEnabled() {
        return this.driver.findElement(this.locator).isEnabled();
    }

}

