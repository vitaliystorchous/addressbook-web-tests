package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreHelper extends HelperBase {

    public StoreHelper(WebDriver wd) { super(wd); }

    public void closeModal() {
        click(By.cssSelector(".f-overlay-close.f-overlay-close-x"));
    }
}
