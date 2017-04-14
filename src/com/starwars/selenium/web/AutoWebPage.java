package com.starwars.selenium.web;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Mandar Abhyankar on 4/14/2017.
 * Last updated by Mandar Abhyankar on 4/14/2017.
 * <p>
 * This class contains methods for...
 * 1)waitForPageElement - Waits for given WebElement to load.
 * 2)doesElementExist - Checks if a WebElement exists on a page.
 * 3)getElementText - Retrieves the text attached to a given WebElement.
 * 4)selectFromDropDown - Selects a element from dropdown menu using either visible text or index.
 * 5)scrollToElement - Brings a given WebElement present on a page in view.
 */
public abstract class AutoWebPage {

    protected WebDriver webDriver;

    /**
     * Constructor calls the waitForPageLoad method implemented by the user.
     *
     * @param tempDriver
     * @throws Exception
     */
    public AutoWebPage(WebDriver tempDriver) throws Exception {

        webDriver = tempDriver;
        if (waitForPageLoad()) {


        } else {

            throw new Exception("The expected page was not loaded");
        }

    }

    /**
     * This method waits for a WebElement on a page to load.
     *
     * @param byLocator
     * @param driver
     */
    public void waitForPageElement(By byLocator, WebDriver driver) {

        WebDriverWait waitForElement = new WebDriverWait(driver, 60);
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    /**
     * This method checks if a WebElement exists on a page.
     *
     * @param byLocator
     * @param driver
     * @return
     */
    public boolean doesElementExist(By byLocator, WebDriver driver) {

        waitForPageElement(byLocator, driver);
        Boolean isPresent = driver.findElements(byLocator).size() > 0;
        return isPresent;
    }

    /**
     * This method is used to retrieve text attached to a WebElement.
     *
     * @param byLocator
     * @param driver
     * @return
     */
    public String getElementText(By byLocator, WebDriver driver) {

        String elementText = null;
        elementText = driver.findElement(byLocator).getText();
        return elementText;
    }

    /**
     * This method is used to select a value from a dropdown using visible text.
     *
     * @param byLocator
     * @param selectValue
     * @param driver
     */
    public void selectFromDropdown(By byLocator, String selectValue, WebDriver driver) {

        waitForPageElement(byLocator, driver);
        Select selectFromDropdown = new Select(driver.findElement(byLocator));
        selectFromDropdown.selectByVisibleText(selectValue);

    }

    /**
     * This method is used to select a value from a dropdown using index.
     *
     * @param byLocator
     * @param index
     * @param driver
     */
    public void selectFromDropdown(By byLocator, int index, WebDriver driver) {

        waitForPageElement(byLocator, driver);
        Select selectFromDropdown = new Select(driver.findElement(byLocator));
        selectFromDropdown.selectByIndex(index);

    }

    /**
     * This method is used to bring a given WebElement on Web page in view.
     *
     * @param byLocator
     * @param driver
     */
    public void scrollToElement(By byLocator, WebDriver driver) {

        JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
        WebElement scrollToElement = driver.findElement(byLocator);
        jsExecute.executeScript("arguments[0].scrollIntoView();", scrollToElement);

    }

    /**
     * This method waits for the page to load. Has to be implemented by the user.
     *
     * @return
     */
    public abstract boolean waitForPageLoad();


}
