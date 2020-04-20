package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.COLLECTION;

public class DeleteCollectionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(COLLECTION)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(COLLECTION).withName(app.collectionName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem collection = MenuEditorItem.getItem(before, COLLECTION);
        app.menuEditor().deleteItem(collection);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(collection);
        Assert.assertEquals(after, before);
    }
}
