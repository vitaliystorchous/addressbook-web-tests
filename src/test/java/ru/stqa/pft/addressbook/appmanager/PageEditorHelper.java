package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageEditorHelper extends HelperBase {

    public PageEditorHelper(WebDriver wd) { super(wd); }

    public void addFirstTextSection() {
        switchFrame(getElement(By.id("editorFrame")));
        click(By.cssSelector(".js-btn-add-section"));
        switchToBaseFrame();
        click(By.cssSelector(".headline_01"));
        click(By.cssSelector(".btn_save"));
    }

}
