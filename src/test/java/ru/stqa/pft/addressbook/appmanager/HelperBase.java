package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HelperBase {
    protected WebDriver wd;
    protected WebDriverWait wait;
    protected long waitDurationSec = 10;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
        this.wait = new WebDriverWait(wd, Duration.ofSeconds(waitDurationSec));
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wait.until(elementToBeClickable(locator));
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            wait.until(presenceOfElementLocated(locator));
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.findElement(locator);
            wd.manage().timeouts().implicitlyWait(waitDurationSec, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isElementPresent(String byText) {
        try {
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.findElement(By.xpath("//*[.='" + byText + "']"));
            wd.manage().timeouts().implicitlyWait(waitDurationSec, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected void switchFrame(WebElement frameElement) {
        wd.switchTo().frame(frameElement);
    }

    protected void switchToBaseFrame() {
        wd.switchTo().defaultContent();
    }

    protected WebElement getElement(By locator) {
        return wd.findElement(locator);
    }

    protected void editElementsContent(WebElement element, String text) {
        ((JavascriptExecutor)wd).executeScript("var ele=arguments[0]; ele.innerHTML = '" + text + "';", element);
    }

    public void pause(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
