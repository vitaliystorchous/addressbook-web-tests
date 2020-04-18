package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameSubmenuTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.SUBMENU, app.submenuName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.SUBMENU).withName(app.submenuName));
        }
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedSubmenu = MenuEditorItem.getItem(before, MenuEditorItem.Type.SUBMENU, app.submenuName);
        before.remove(renamedSubmenu);
        before.add(renamedSubmenu.withName("Renamed test submenu (*Selenium*)"));
        app.menuEditor().renameItem(renamedSubmenu); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
