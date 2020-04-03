package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuEditorHelper extends HelperBase {

    public MenuEditorHelper(WebDriver wd) {
        super(wd);
    }

    private void clickMoreOptions(String pageName) {
        click(By.xpath("//span[contains(.,\"" + pageName +"\")]/../..//button"));
    }

    public void deleteSelectedPage(String pageName) {
        clickMoreOptions(pageName);
        click(By.cssSelector(".site-menu-editor-item-actions__action-link--delete-permanently"));
        click(By.cssSelector(".btn-red"));
    }

    public void renameSelectedPage(String pageName) {
        clickMoreOptions(pageName);
        click(By.xpath("//span[contains(.,'Rename')]"));
        type(By.id("page-title"), "qwer Test page");
        click(By.cssSelector(".btn-primarycolor"));
    }

    public void openSelectedPageInPageEditor(String pageName) {
        click(By.xpath("//span[contains(.,\"" + pageName + "\")]/../..//a[@class=\"pages-editor-item__edit-button text-12\"]"));
    }

    public void setSelectedPageAsHomepage(String pageName) {
        clickMoreOptions(pageName);
        click(By.xpath("//span[contains(.,'Set as Homepage')]"));
    }

    public void addSelectedPageToMenu(String pageName) {
        clickMoreOptions(pageName);
        click(By.xpath("//span[contains(.,'Add to Menu')]"));
    }
}
