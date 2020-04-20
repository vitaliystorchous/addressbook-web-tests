package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class RenameSubmenuTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(SUBMENU, app.submenuName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(SUBMENU).withName(app.submenuName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedSubmenu = MenuEditorItem.getItem(before, SUBMENU, app.submenuName);
        before.remove(renamedSubmenu);
        before.add(renamedSubmenu.withName("Renamed test submenu (*Selenium*)"));
        app.menuEditor().renameItem(renamedSubmenu);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
