package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

public class MenuEditorHelper extends HelperBase {

    NavigationHelper navigationHelper = new NavigationHelper(wd);

    public MenuEditorHelper(WebDriver wd) {
        super(wd);
    }

    private void clickMoreOptions(String pageName) {
        click(By.xpath("//span[contains(.,\"" + pageName +"\")]/../..//button"));
    }

    private void clickMoreOptions(int index) {
        wd.findElements(By.cssSelector(".site-menu-item-actions-cta--meatball-svg")).get(index).click();
    }

    public void deleteSelectedPage(String pageName) {
        clickMoreOptions(pageName);
        click(By.cssSelector(".site-menu-editor-item-actions__action-link--delete-permanently"));
        click(By.cssSelector(".btn-red"));
    }

    public void deleteSelectedPage(int index) {
        clickMoreOptions(index);
        click(By.cssSelector(".site-menu-editor-item-actions__action-link--delete-permanently"));
        click(By.cssSelector(".btn-red"));
    }

    public void renameSelectedPage(String pageName, String newName) {
        clickMoreOptions(pageName);
        click(By.xpath("//span[contains(.,'Rename')]"));
        type(By.id("page-title"), newName);
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

    /*public void createCustomPage(String pageName) {
        click(By.xpath("//button[@class=\"btn\"]"));
        click(By.cssSelector(".site-menu-action-header li:nth-child(3)"));
        type(By.cssSelector(".page-type-modal-input"), pageName);
        click(By.cssSelector(".btn-primarycolor"));

        isElementPresent(By.cssSelector(".page-editor-header--custom-page"));
    }*/

    public void createMenuEditorItem(MenuEditorItem item) {
        click(By.xpath("//button[@class=\"btn\"]"));

        switch (item.getType()) {
            case GALLERY: click(By.cssSelector(".site-menu-action-header li:nth-child(1)")); break;
            case COLLECTION: click(By.cssSelector(".site-menu-action-header li:nth-child(2)")); break;
            case CUSTOM_PAGE: click(By.cssSelector(".site-menu-action-header li:nth-child(3)")); break;
            case BLOG: case BLOG_POST: click(By.cssSelector(".site-menu-action-header li:nth-child(4)")); break;
            case EXTERNAL_LINK: click(By.cssSelector(".site-menu-action-header li:nth-child(5)")); break;
            case PROOFING_PROJECT: click(By.cssSelector(".site-menu-action-header li:nth-child(6)")); break;
            case SUBMENU: click(By.cssSelector(".site-menu-action-header li:nth-child(7)")); break;
            case STORE: case STORE_PRODUCT: click(By.cssSelector(".site-menu-action-header li:nth-child(8)")); break;
        }

        switch (item.getType()) {
            case GALLERY: case CUSTOM_PAGE: case COLLECTION: case BLOG: case BLOG_POST: case PROOFING_PROJECT: case STORE: case STORE_PRODUCT: {
                type(By.cssSelector(".page-type-modal-input"), item.getName());
                click(By.cssSelector(".btn-primarycolor"));
                break;
            }

            case SUBMENU: {
                type(By.cssSelector("input#sub-menu-name"), item.getName());
                click(By.cssSelector(".btn-primarycolor"));
                break;
            }

            case EXTERNAL_LINK: {
                type(By.cssSelector("input#link-title"), item.getName());
                type(By.cssSelector("input#link-url"), "https://www.google.com/");
                click(By.cssSelector(".btn-primarycolor"));
                break;
            }
        }

        switch (item.getType()) {
            case GALLERY: case COLLECTION: case CUSTOM_PAGE: case PROOFING_PROJECT: case STORE_PRODUCT: case BLOG_POST:
                isElementPresent(By.cssSelector(".page-editor-header"));
                break;
            case STORE: case BLOG:
                isElementPresent(By.cssSelector(".format-ui.page-header.page-index-header"));
                break;
            case EXTERNAL_LINK: case SUBMENU:
                break;

        }
    }

    public void checkCustomPagePresence(String customPageName) {
        if(! isElementPresent(customPageName)) {
            createMenuEditorItem(new MenuEditorItem(MenuEditorItem.Type.CUSTOM_PAGE, customPageName));
            navigationHelper.goToPagesPage();
        }
    }

    public int getPagesCount() {
        return wd.findElements(By.cssSelector(".site-menu-editor-item")).size();
    }
}
