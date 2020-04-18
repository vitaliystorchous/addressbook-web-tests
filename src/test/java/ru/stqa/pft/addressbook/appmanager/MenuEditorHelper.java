package ru.stqa.pft.addressbook.appmanager;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class MenuEditorHelper extends HelperBase {

    NavigationHelper goTo = new NavigationHelper(wd);
    PageEditorHelper pageEditorHelper = new PageEditorHelper(wd);
    BlogEditorHelper blogEditorHelper = new BlogEditorHelper(wd);
    StoreHelper storeHelper = new StoreHelper(wd);


    public MenuEditorHelper(WebDriver wd) {
        super(wd);
    }

    private void clickMoreOptions(String pageName) {
        click(By.xpath("//span[contains(.,\"" + pageName +"\")]/../..//button"));
    }

    private void clickMoreOptions(int index) {
        wd.findElements(By.cssSelector(".site-menu-item-actions-cta--meatball-svg")).get(index).click();
    }

    private void clickMoreOptionsByItemId(int dataId) {
        wd.findElement(By.xpath("//*[contains(@data-id, '" + dataId +"')]//button[contains(@class, 'site-menu-item-actions-cta')]")).click();
    }

    private void clickEditById(int dataId) {
        wd.findElement(By.xpath("//*[contains(@data-id, '" + dataId +"')]//*[contains(@class, 'pages-editor-item__edit-button')]")).click();
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

    public void deleteItem(MenuEditorItem item) {
        clickMoreOptionsByItemId(item.getDataId());
        String selector = ".site-menu-editor-item-actions__action-link--delete-permanently";
        switch (item.getType()) {
            case PROOFING_PROJECT: case GALLERY: case CUSTOM_PAGE: case COLLECTION: case SUBMENU: case EXTERNAL_LINK: {
                try {
                    click(By.cssSelector(selector));
                } catch (StaleElementReferenceException ex) {
                    clickMoreOptionsByItemId(item.getDataId());
                    click(By.cssSelector(selector));
                }
                break;
            }

            case BLOG: case BLOG_POST: case STORE_PRODUCT: case STORE: {
                System.out.println("Blog, blog post, store product and store can't be deleted through menu editor");
                break;
            }
        }
        click(By.cssSelector(".btn-red"));
    }

    public void renameSelectedPage(String pageName, String newName) {
        clickMoreOptions(pageName);
        click(By.xpath("//ul/li/span"));
        type(By.id("page-title"), newName);
        click(By.cssSelector(".btn-primarycolor"));
    }

    public void renameItem(MenuEditorItem item) {
        clickMoreOptionsByItemId(item.getDataId());

        switch (item.getType()) {
            case BLOG: case STORE: case CUSTOM_PAGE: case COLLECTION: case GALLERY: case PROOFING_PROJECT: {
                click(By.xpath("//ul/li[1]/span"));
                type(By.id("page-title"), item.getName());
                break;
            }

            case EXTERNAL_LINK: case SUBMENU: {
                clickEditById(item.getDataId());
                if(item.getType() == Type.EXTERNAL_LINK) { type(By.id("link-title"), item.getName()); }
                else if(item.getType() == Type.SUBMENU) { type(By.id("sub-menu-name"), item.getName()); }
                break;
            }
        }

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

    public void createItem(MenuEditorItem item) {
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
                pageEditorHelper.waitPageEditorOpened();
                goTo.pagesPage();
                break;
            }

            case BLOG: case STORE: {
                type(By.cssSelector("input[name='page[name]']"), item.getName());
                click(By.cssSelector(".btn-primarycolor"));
                if(item.getType() == Type.BLOG) {
                    blogEditorHelper.waitBlogEditorOpened();
                } else if(item.getType() == Type.STORE) {
                    storeHelper.waitStoreOpened();
                    storeHelper.closeModal();
                }
                goTo.pagesPage();
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
            createItem(new MenuEditorItem().withType(Type.CUSTOM_PAGE).withName(customPageName));
        }
    }

    public int getPagesCount() {
        return wd.findElements(By.cssSelector(".site-menu-editor-item")).size();
    }

    //метод itemsList() устаревший - нужно заменить все случаи использования на метод all() при этом убрать сортировки так как сравниваються множества а не упорядоченые списки элементов
    public List<MenuEditorItem> itemsList() {
        List<MenuEditorItem> items = new ArrayList<MenuEditorItem>();
        List<WebElement> elements = wd.findElements(By.cssSelector(".site-menu-editor-item-flex-wrap"));

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        for(WebElement element : elements) {
            Type type = getElementType(element);
            String elementName = getElementName(element, type);
            int elementDataId = getElementDataId(element);
            MenuEditorItem item = new MenuEditorItem().withDataId(elementDataId).withType(type).withName(elementName);
            item.withHomepage(element.findElements(By.xpath(".//*[contains(@class, 'site-menu-editor-item-home-icon')]")).size() != 0);
            item.withInMenu(element.findElements(By.xpath("./../../../*[contains(@class, 'pages-editor-sortable-wrap--in-menu')]")).size() != 0);
            items.add(item);
        }
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return items;
    }

    public boolean isBlogPresent(Set<MenuEditorItem> items) {
        for(MenuEditorItem item : items) {
            if(item.getType() == Type.BLOG) { return true; }
        }
        return false;
    }

    public boolean isStorePresent(Set<MenuEditorItem> items) {
        for(MenuEditorItem item : items) {
            if(item.getType() == Type.STORE) { return true; }
        }
        return false;
    }

    public Set<MenuEditorItem> allItems() {
        Set<MenuEditorItem> items = new HashSet<MenuEditorItem>();
        List<WebElement> elements = wd.findElements(By.cssSelector(".site-menu-editor-item-flex-wrap"));

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        for(WebElement element : elements) {
            Type type = getElementType(element);
            String elementName = getElementName(element, type);
            int elementDataId = getElementDataId(element);
            MenuEditorItem item = new MenuEditorItem().withDataId(elementDataId).withType(type).withName(elementName);
            item.withHomepage(element.findElements(By.xpath(".//*[contains(@class, 'site-menu-editor-item-home-icon')]")).size() != 0);
            item.withInMenu(element.findElements(By.xpath("./../../../*[contains(@class, 'pages-editor-sortable-wrap--in-menu')]")).size() != 0);
            items.add(item);
        }
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return items;
    }

    public boolean isItemPresent(Type type, String name) {
        Set<MenuEditorItem> items = allItems();
        for(MenuEditorItem item : items) {
            if(item.getType() == type && item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    //inner service methods

    @NotNull
    private String getElementName(WebElement element, Type type) {
        if(type != Type.SUBMENU) {
            return element.findElement(By.xpath(".//*[@class='site-menu-editor-item-name']")).getAttribute("innerText");
        }
        else {
            return element.findElement(By.xpath("//*[@class='site-menu-editor-item-sub-menu-name']"))
                    .getAttribute("innerText")
                    .replace("\nEdit", "");
        }
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
