package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.CUSTOM_PAGE;

public class CreateCustomPageTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validItems() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {new MenuEditorItem().withType(CUSTOM_PAGE).withName("test page 1")});
        list.add(new Object[] {new MenuEditorItem().withType(CUSTOM_PAGE).withName("test page 2")});
        list.add(new Object[] {new MenuEditorItem().withType(CUSTOM_PAGE).withName("test page 3")});
        return list.iterator();
    }

    @Test (dataProvider = "validItems")
    public void test(MenuEditorItem customPage) {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        app.menuEditor().createItem(customPage);
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size() + 1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(customPage.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt()))));
    }

}
