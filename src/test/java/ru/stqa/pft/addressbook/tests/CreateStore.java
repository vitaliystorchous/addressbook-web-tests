package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

//этот тест нужно доработать в отдельном порядке (сейчас нету смысла тратить на него много времени так как магазин нельзя удалить)
public class CreateStore extends TestBase {

    @Test (enabled = false)
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        if(! app.menuEditor().isStorePresent(before)) {
            MenuEditorItem store = new MenuEditorItem().withType(Type.STORE).withName(app.storeName);
            app.menuEditor().createItem(store);
            Set<MenuEditorItem> after = app.menuEditor().allItems();
            Assert.assertEquals(after.size(), before.size() + 1);

            store.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt());
            before.add(store);
            Assert.assertEquals(after, before);
        } else {
            System.out.println("Store is already present");
        }
    }
}
