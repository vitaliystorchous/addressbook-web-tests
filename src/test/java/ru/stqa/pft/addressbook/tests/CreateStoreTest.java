package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.STORE;

//этот тест нужно доработать в отдельном порядке (сейчас нету смысла тратить на него много времени так как магазин нельзя удалить)
public class CreateStoreTest extends TestBase {

    @Test (enabled = false)
    public void test() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        if(! app.menuEditor().isStorePresent(before)) {
            MenuEditorItem store = new MenuEditorItem().withType(STORE).withName(app.storeName);
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
