package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreHelper extends HelperBase {

    WebDriverWait wait;

    public StoreHelper(WebDriver wd) {
        super(wd);
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
    }

    public void waitStoreOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".format-ui.page-header.page-index-header")));
    }

    public void closeModal() {
        click(By.cssSelector(".f-overlay-close.f-overlay-close-x"));
    }
}
