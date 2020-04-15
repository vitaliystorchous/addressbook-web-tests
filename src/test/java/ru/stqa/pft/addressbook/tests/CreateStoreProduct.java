package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;

public class CreateStoreProduct extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        if(! app.menuEditor().isStorePresent(before)) {
            MenuEditorItem store = new MenuEditorItem().withType(Type.STORE).withName(app.storeName);
            app.menuEditor().createItem(store);
            before.add(store);
        }
        app.menuEditor().createItem(new MenuEditorItem().withType(Type.STORE_PRODUCT).withName(app.storeProductName));
        List<MenuEditorItem> after = app.menuEditor().itemsList();
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
