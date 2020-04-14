package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;

public class CreateStore extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        if(! app.getMenuEditorHelper().isStorePresent(before)) {
            MenuEditorItem item = new MenuEditorItem(Type.STORE, app.storeName);
            app.getMenuEditorHelper().createMenuEditorItem(item);
            app.getStoreHelper().waitStoreOpened();
            app.getStoreHelper().closeModal();
            app.getNavigationHelper().goToPagesPage();
            List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
            Assert.assertEquals(after.size(), before.size() + 1);

            before.add(item);
            Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
            before.sort(byId);
            after.sort(byId);
            Assert.assertEquals(after, before);
        } else {
            System.out.println("Store is already present");
        }
    }
}
