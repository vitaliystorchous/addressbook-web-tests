package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.List;

public class CreateStoreProduct extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        if(! app.getMenuEditorHelper().isStorePresent(before)) {
            MenuEditorItem store = new MenuEditorItem(Type.STORE, app.storeName);
            app.getMenuEditorHelper().createMenuEditorItem(store);
            app.getStoreHelper().waitStoreOpened();
            app.getStoreHelper().closeModal();
            app.getNavigationHelper().goToPagesPage();
            before.add(store);
        }
        app.getMenuEditorHelper().createMenuEditorItem(new MenuEditorItem(Type.STORE_PRODUCT, app.storeProductName));
        app.getPageEditorHelper().waitPageEditorOpened();
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size());
    }
}
