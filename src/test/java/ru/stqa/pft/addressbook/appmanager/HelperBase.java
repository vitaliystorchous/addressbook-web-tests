package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HelperBase {
    protected WebDriver wd;
    protected WebDriverWait wait;
    protected long waitDurationSec = 10;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
        this.wait = new WebDriverWait(wd, Duration.ofSeconds(waitDurationSec));
    }

    protected void click(By locator) {
        try {
            wd.findElement(locator).click();
            return;
        } catch (NoSuchElementException ex) {
            return;
        }
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
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
            wd.findElement(By.xpath("//*[contains(., '" + byText + "')]"));
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
}
