package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameCollectionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.COLLECTION, app.collectionName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.COLLECTION).withName(app.collectionName));
        }
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedCollection = MenuEditorItem.getItem(before, MenuEditorItem.Type.COLLECTION, app.collectionName);
        before.remove(renamedCollection);
        before.add(renamedCollection.withName("Renamed " + renamedCollection.getName()));
        app.menuEditor().renameItem(renamedCollection); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
