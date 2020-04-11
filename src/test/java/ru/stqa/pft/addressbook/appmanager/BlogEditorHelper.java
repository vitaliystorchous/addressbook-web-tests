package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlogEditorHelper extends HelperBase {

    WebDriverWait wait;

    public BlogEditorHelper(WebDriver wd) {
        super(wd);
        this.wait = new WebDriverWait(wd, Duration.ofSeconds(30));
    }

    public void waitBlogEditorOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".format-ui.page-header.page-index-header")));
    }
}
