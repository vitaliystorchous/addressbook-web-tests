package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class RenameStoreTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(STORE)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(STORE).withName(app.storeName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedStore = MenuEditorItem.getItem(before, STORE);
        before.remove(renamedStore);
        before.add(renamedStore.withName("Renamed test store (*Selenium*)"));
        app.menuEditor().renameItem(renamedStore);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }

    @AfterMethod
    public void returnBackCondition() {
        Set<MenuEditorItem> items = app.menuEditor().allItems();
        MenuEditorItem renamedStore = MenuEditorItem.getItem(items, STORE);
        items.remove(renamedStore);
        items.add(renamedStore.withName(app.storeName));
        app.menuEditor().renameItem(renamedStore);
        app.pause(1);
    }
}
