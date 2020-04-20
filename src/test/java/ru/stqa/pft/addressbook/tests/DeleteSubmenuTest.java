package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.SUBMENU;

public class DeleteSubmenuTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(SUBMENU)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(SUBMENU).withName(app.submenuName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem submenu = MenuEditorItem.getItem(before, SUBMENU);
        app.menuEditor().deleteItem(submenu);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(submenu);
        Assert.assertEquals(after, before);
    }
}
