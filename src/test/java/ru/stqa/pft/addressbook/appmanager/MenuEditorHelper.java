package ru.stqa.pft.addressbook.appmanager;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MenuEditorHelper extends HelperBase {

    NavigationHelper navigationHelper = new NavigationHelper(wd);
    PageEditorHelper pageEditorHelper = new PageEditorHelper(wd);

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
        click(By.xpath("//ul/li/span"));
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
            case GALLERY: case CUSTOM_PAGE: case COLLECTION: case BLOG_POST: case PROOFING_PROJECT: case STORE_PRODUCT: {
                type(By.cssSelector(".page-type-modal-input"), item.getName());
                click(By.cssSelector(".btn-primarycolor"));
                break;
            }

            case BLOG: case STORE: {
                type(By.cssSelector("input[name='page[name]']"), item.getName());
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
    }

    public void checkCustomPagePresence(String customPageName) {
        if(! isElementPresent(customPageName)) {
            createMenuEditorItem(new MenuEditorItem(Type.CUSTOM_PAGE, customPageName));
            pageEditorHelper.waitPageEditorOpened();
            navigationHelper.goToPagesPage();
        }
    }

    public int getPagesCount() {
        return wd.findElements(By.cssSelector(".site-menu-editor-item")).size();
    }

    public List<MenuEditorItem> getMenuItemsList() {
        List<MenuEditorItem> items = new ArrayList<MenuEditorItem>();
        List<WebElement> elements = wd.findElements(By.cssSelector(".site-menu-editor-item-flex-wrap"));

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        for(WebElement element : elements) {
            Type type = getElementType(element);
            String elementName = getElementName(element);
            int elementDataId = getElementDataId(element);
            MenuEditorItem item = new MenuEditorItem(elementDataId, type, elementName);
            item.setHomepage(element.findElements(By.xpath(".//*[contains(@class, 'site-menu-editor-item-home-icon')]")).size() != 0);
            item.setInMenu(element.findElements(By.xpath("./../../../*[contains(@class, 'pages-editor-sortable-wrap--in-menu')]")).size() != 0);
            items.add(item);
        }
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return items;
    }

    public boolean isBlogPresent(List<MenuEditorItem> items) {
        for(MenuEditorItem item : items) {
            if(item.getType() == Type.BLOG) { return true; }
        }
        return false;
    }

    public boolean isStorePresent(List<MenuEditorItem> items) {
        for(MenuEditorItem item : items) {
            if(item.getType() == Type.STORE) { return true; }
        }
        return false;
    }

    
    
    //inner service methods

    @NotNull
    private String getElementName(WebElement element) {
        String elementName = element.getText();
        if(elementName.contains("HOME")) { elementName = elementName.replace("\nHOME", ""); }
        return elementName;
    }

    @NotNull
    private Type getElementType(WebElement element) {
        Type type;
        String editButtonTooltip = element.findElement(By.xpath(".//*[contains(@class, 'pages-editor-item__edit-button')]")).getAttribute("data-tooltip"); // особенно на этой строчке локатор нужно переделать
        switch (editButtonTooltip) {
            case "Rename Submenu": type = Type.SUBMENU; break;
            case "Edit Link": type = Type.EXTERNAL_LINK; break;
            case "Manage Posts": type = Type.BLOG; break;
            case "Edit Store": type = Type.STORE; break;
            case "Edit Project": type = Type.PROOFING_PROJECT; break;
            default: {
                String iconId = element.findElement(By.xpath(".//*[contains(@id, 'ico-')]")).getAttribute("id");
                switch (iconId) {
                    case "ico-gallery": type = Type.GALLERY; break;
                    case "ico-custompage": type = Type.CUSTOM_PAGE; break;
                    default: type = Type.COLLECTION; break;
                }
            }
        }
        return type;
    }

    private int getElementDataId(WebElement element) {
        String dataId = element.findElement(By.xpath("./..")).getAttribute("data-id");
        if(dataId.contains("_page")) {
            dataId = dataId.replace("_page", "");
        } else {
            dataId = dataId.replace("_group", "");
        }

        return Integer.parseInt(dataId);
    }
}
