package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.STORE;

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
        Items before = app.menuEditor().allItems();
        MenuEditorItem storeToRename = MenuEditorItem.getItem(before, STORE);
        MenuEditorItem renamedStore = storeToRename.withName("Renamed test store (*Selenium*)");
        app.menuEditor().renameItem(storeToRename);
        assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(storeToRename).withAdded(renamedStore)));
    }

    @AfterMethod
    public void returnBackCondition() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem storeToRename = MenuEditorItem.getItem(before, STORE);
        storeToRename.withName(app.storeName);
        app.menuEditor().renameItem(storeToRename);
        app.pause(2);
    }
}
