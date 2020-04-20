package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Set;

public class CreateStoreProductTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        if(! app.menuEditor().isStorePresent(before)) {
            MenuEditorItem store = new MenuEditorItem().withType(Type.STORE).withName(app.storeName);
            app.menuEditor().createItem(store);
            before.add(store);
        }
        app.menuEditor().createItem(new MenuEditorItem().withType(Type.STORE_PRODUCT).withName(app.storeProductName));
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
