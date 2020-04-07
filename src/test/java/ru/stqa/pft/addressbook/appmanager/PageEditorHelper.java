package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageEditorHelper extends HelperBase {

    public PageEditorHelper(WebDriver wd) { super(wd); }

    public void goBackToPagesFromPageEditor() {
        wd.findElement(By.cssSelector(".whitespace-no-wrap")).click();
    }

    public void addFirstTextSection() {
        switchFrame(getElement(By.id("editorFrame")));
        click(By.cssSelector(".js-btn-add-section"));
        switchToBaseFrame();
        click(By.cssSelector(".headline_01"));
        click(By.cssSelector(".btn_save"));
    }

    public void editTextOfFirstTextSection() {
        switchFrame(getElement(By.id("editorFrame")));
        click(By.id("title_1"));
        editElementsContent(getElement(By.id("title_1")), "test");
        switchToBaseFrame();
        click(By.cssSelector(".btn_save"));
    }

    public void deleteFirstTextSection() {
        switchFrame(getElement(By.id("editorFrame")));
        click(By.cssSelector(".delete_module"));
        switchToBaseFrame();
        click(By.cssSelector(".btn_save"));
    }
}
