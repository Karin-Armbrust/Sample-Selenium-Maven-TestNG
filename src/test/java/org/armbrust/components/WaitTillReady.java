package org.armbrust.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTillReady {
    private final int secondsTimeout;
    private final WebDriver myDriver;

    // Constructor
    public WaitTillReady(WebDriver myDriver, int seconds) {
        this.myDriver = myDriver;
        this.secondsTimeout = seconds;
    }

    // A Class that extends this MUST implement this class
    public void elementNowReady() {
        throw new RuntimeException("Function elementNowReady has not been implemented.");
    }

    // Function to check if the element is ready
    public boolean elementReady() {
        try {
            elementNowReady();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    // Wait until the specific element is ready
    public void waitTilReady() {
        ExpectedCondition<Boolean> nowReady = myDriver -> {
            elementNowReady();
            return true;
        };

        new WebDriverWait(this.myDriver, this.secondsTimeout)
                .ignoring(IllegalStateException.class)
                .until(nowReady);
    }
}

