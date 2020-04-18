package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameStoreTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.STORE)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.STORE).withName(app.storeName));
        }
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedStore = MenuEditorItem.getItem(before, MenuEditorItem.Type.STORE);
        before.remove(renamedStore);
        before.add(renamedStore.withName("Renamed " + renamedStore.getName()));
        app.menuEditor().renameItem(renamedStore); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
