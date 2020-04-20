package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

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
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedCollection = MenuEditorItem.getItem(before, COLLECTION, app.collectionName);
        before.remove(renamedCollection);
        before.add(renamedCollection.withName("Renamed test collection (*Selenium)"));
        app.menuEditor().renameItem(renamedCollection);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
