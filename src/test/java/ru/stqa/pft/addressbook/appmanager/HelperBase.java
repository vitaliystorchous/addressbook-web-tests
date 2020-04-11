package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
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
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isElementPresent(String byText) {
        try {
            wd.findElement(By.xpath("//*[contains(., '" + byText + "')]"));
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
