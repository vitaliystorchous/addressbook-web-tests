package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateStoreProductTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(Type.STORE)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(Type.STORE).withName(app.storeName));
        }
    }

    @Test
    public void theTest() {
        Items before = app.menuEditor().allItems();
        app.menuEditor().createItem(new MenuEditorItem().withType(Type.STORE_PRODUCT).withName(app.storeProductName));
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size()));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before));
    }
}
