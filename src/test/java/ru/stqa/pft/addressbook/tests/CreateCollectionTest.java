package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreateCollectionTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem collection = new MenuEditorItem().withType(Type.COLLECTION).withName(app.collectionName);
        app.menuEditor().createItem(collection);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() + 1);

        collection.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt());
        before.add(collection);
        Assert.assertEquals(after, before);
    }

}
