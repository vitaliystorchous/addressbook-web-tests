package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class CreateCollectionTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem collection = new MenuEditorItem().withType(COLLECTION).withName(app.collectionName);
        app.menuEditor().createItem(collection);
        assertThat(app.menuEditor().getItemsCount(), equalTo(before.size() +1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(collection.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt()))));
    }

}
