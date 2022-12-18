package org.armbrust.components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class SelectMenuComponent extends WaitTillReady {
    private final By locator;
    private final WebDriver driver;

    // Constructor
    public SelectMenuComponent(WebDriver driver, final By locator) {
        super(driver, 10);
        this.driver = driver;
        this.locator = locator;
    }

    // Check that the Select Menu is ready and clickable
    public void elementNowReady() {
        WebElement element = this.driver.findElement(this.locator);

        if (element.isDisplayed() && element.isEnabled()) {
            return;
        } else {
            throw new IllegalStateException(String.format("The Select Menu %s is not ready.", this.locator));
        }
    }

    // Select Single Value By Value
    public void selectSingleItemByValue(String selection) {
        WebElement element = this.driver.findElement(this.locator);
        final Select singleSelect = new Select(element);
        singleSelect.selectByValue(selection);
    }

    // Select Single Value By Visible Text
    public void selectSingleItemByValueByVisibleText(String selection) {
        WebElement element = this.driver.findElement(this.locator);
        final Select singleSelect = new Select(element);
        singleSelect.selectByVisibleText(selection);
    }

    // Validate Single Selection
    public void validateSingleSelection(String selection) {
        WebElement element = this.driver.findElement(this.locator);
        final Select singleSelect = new Select(element);
        List<WebElement> selectedOptionList = singleSelect.getAllSelectedOptions();
        Assert.assertEquals(1, selectedOptionList.size());
        Assert.assertEquals(selection, selectedOptionList.get(0).getText());
    }

    // Validate Multi Selection
    public List<WebElement> getMultiSelections() {
        WebElement element = this.driver.findElement(this.locator);
        final Select selections = new Select(element);
        List<WebElement> selectedOptionList = selections.getAllSelectedOptions();
        return selectedOptionList;
    }

    // Deselect multi option menu
    public void deselectAll() {
        WebElement element = this.driver.findElement(this.locator);
        final Select selections = new Select(element);
        selections.deselectAll();
    }

    // Get number of options in Select
    public int getNumberOfOptions() {
        WebElement element = this.driver.findElement(this.locator);
        final Select selections = new Select(element);
        return selections.getOptions().size();
    }

    // Check if it's a single select menu
    public boolean isMultipleSelect() {
        WebElement element = this.driver.findElement(this.locator);
        final Select selectMenu = new Select(element);
        return selectMenu.isMultiple();
    }
}

