package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.COLLECTION;

public class RenameCollectionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(COLLECTION, app.collectionName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(COLLECTION).withName(app.collectionName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem collectionToRename = MenuEditorItem.getItem(before, COLLECTION, app.collectionName);
        MenuEditorItem renamedCollection = collectionToRename.withName("Renamed test collection (*Selenium*)");
        app.menuEditor().renameItem(collectionToRename);
        assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(collectionToRename).withAdded(renamedCollection)));
    }
}
