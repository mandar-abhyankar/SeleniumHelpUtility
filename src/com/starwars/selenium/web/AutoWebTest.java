package com.starwars.selenium.web;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Mandar Abhyankar on 4/14/2017.
 * Updated by Mandar Abhyankar on 4/14/2017
 */

public abstract class AutoWebTest {

    protected WebDriver webdriver = null;

    //system properties for Chrome, Internet Explorer and Firefox
    private static final String CHROME_SYSTEM_PROPERTY = "webdriver.chrome.driver";
    private static final String IE_SYSTEM_PROPERTY = "webdriver.ie.driver";
    private static final String FIREFOX_SYSTEM_PROPERTY = "webdriver.firefox.marionette";

    //setting default driver paths for Chrome, Internet Explorer and Firefox
    private static final String DEFAULT_CHROME_DRIVER_PATH = "C:\\www\\drivers\\chrome_driver\\";
    private static final String DEFAULT_IE_DRIVER_PATH = "C:\\www\\drivers\\ie_driver\\";
    private static final String DEFAULT_FIREFOX_DRIVER_PATH = "C:\\www\\drivers\\gecko_driver\\";

    //setting default file path for screenshot files
    private static final String DEFAULT_SCREENSHOT_FILE_PATH = "C:\\www\\screenshots\\";

    /**
     * This method returns an instance of the Chrome webdriver
     *
     * @return
     */
    public WebDriver getChromeDriver() {

        try {
            System.setProperty(CHROME_SYSTEM_PROPERTY, DEFAULT_CHROME_DRIVER_PATH + "ChromeDriver.exe");
        } catch (Exception exp) {

            System.out.println("Cannot set System property for Chrome Driver. Exception:" + exp);
        }
        webdriver = new ChromeDriver();
        return webdriver;

    }

    /**
     * This method returns an instance of the IE webdriver
     *
     * @return
     */
    public WebDriver getIEDriver() {

        try {
            System.setProperty(IE_SYSTEM_PROPERTY, DEFAULT_IE_DRIVER_PATH + "IEDriverServer.exe");
        } catch (Exception exp) {

            System.out.println("Cannot set System property for Internet Explorer Driver. Exception:" + exp);
        }
        webdriver = new InternetExplorerDriver();
        return webdriver;

    }

    /**
     * Method to return Firefox WebDriver
     *
     * @return
     */
    public WebDriver getFirefoxDriver() {

        try {
            System.setProperty(FIREFOX_SYSTEM_PROPERTY, DEFAULT_FIREFOX_DRIVER_PATH + "GeckoDriver.exe");
        } catch (Exception exp) {

            System.out.println("Cannot set System property for Firefox Driver. Exception:" + exp);
        }
        webdriver = new FirefoxDriver();
        return webdriver;
    }

    /**
     * Method to maximize the browser window
     *
     * @param driver
     */
    public void maximizeBrowserWindow(WebDriver driver) {

        driver.manage().window().maximize();

    }

    /**
     * This method is used to capture Screenshot
     *
     * @param driver
     * @param fileName
     * @param filePath
     */
    public void captureScreenshot(WebDriver driver, String fileName, String filePath) {

        File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        Calendar calendar = Calendar.getInstance();
        File ScreenshotFile = new File(filePath + fileName + "_" + dateFormat.format(calendar.getTime()) + ".png");
        try {
            FileUtils.copyFile(Screenshot, ScreenshotFile);
        } catch (IOException e) {

            System.out.println("Unable to copy the" + fileName);
        }

    }

    /**
     * This method is used to close and quit the webdriver
     *
     * @param driver
     * @param testStatus
     */
    public void driverShutdown(WebDriver driver, String testStatus) {

        if (testStatus.equals("Pass")) {

            driver.close();
            driver.quit();

        } else {

            captureScreenshot(driver, "Error", DEFAULT_SCREENSHOT_FILE_PATH);

        }


    }

}
