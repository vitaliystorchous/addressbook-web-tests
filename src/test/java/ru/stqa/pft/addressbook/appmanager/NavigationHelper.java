package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationHelper extends HelperBase {

    WebDriverWait wait;

    public NavigationHelper(WebDriver wd) {
        super(wd);
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
    }

    public void goToPagesPage() {
        if(isElementPresent(By.cssSelector(".site-menu-editor-items__wrapper"))) {
            return;
        }
        click(By.xpath("//div[@class=\"format-simple-sidebar\"]//a[@href=\"/site/pages\"]"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".site-menu-editor-items__wrapper")));
    }

    public void goToDashboardPage() {
        if(isElementPresent(By.cssSelector(".dashboard_items"))) {
            return;
        }
        click(By.xpath("//div[@class=\"format-simple-sidebar\"]//a[@href=\"/site/dashboard\"]"));
    }
}
