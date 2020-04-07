package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) { super(wd); }

    public void goToPagesPage() {
        if(isElementPresent(By.cssSelector(".site-menu-editor-items__wrapper"))) {
            return;
        }
        click(By.xpath("//div[@class=\"format-simple-sidebar\"]//a[@href=\"/site/pages\"]"));
    }

    public void goToDashboardPage() {
        if(isElementPresent(By.cssSelector(".dashboard_items"))) {
            return;
        }
        click(By.xpath("//div[@class=\"format-simple-sidebar\"]//a[@href=\"/site/dashboard\"]"));
    }
}
