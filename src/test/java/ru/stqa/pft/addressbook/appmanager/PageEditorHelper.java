package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageEditorHelper extends HelperBase {

    public PageEditorHelper(WebDriver wd) { super(wd); }

    public void addFirstTextSection() {
        switchFrame(getElement(By.cssSelector("iframe#editorFrame")));
        click(By.cssSelector(".js-btn-add-section"));
        switchToBaseFrame();
        click(By.cssSelector(".headline_01"));
        click(By.cssSelector(".js_btn_save_changes"));
    }

    public void editTextOfFirstTextSection() {
        switchFrame(getElement(By.id("editorFrame")));
        click(By.id("title_1"));
        editElementsContent(getElement(By.id("title_1")), "test");
        switchToBaseFrame();
        click(By.cssSelector(".js_btn_save_changes"));
    }

    public void deleteFirstTextSection() {
        switchFrame(getElement(By.id("editorFrame")));
        click(By.cssSelector(".delete_module"));
        switchToBaseFrame();
        click(By.cssSelector(".js_btn_save_changes"));
    }

    public void checkTextSection1Presence() {
        switchFrame(getElement(By.id("editorFrame")));
        if(! isElementPresent(By.cssSelector("._4ORMAT_content_page_row._4ORMAT_content_page_row_short._4ORMAT_module_headline_01._4ormat_sort_item.in-viewport"))) {
            switchToBaseFrame();
            addFirstTextSection();
        } else {
            switchToBaseFrame();
        }
    }

    public void waitPageEditorOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".page-editor-header")));
    }
}
