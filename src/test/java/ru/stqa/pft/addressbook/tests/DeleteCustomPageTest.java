package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.CUSTOM_PAGE;

public class DeleteCustomPageTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(CUSTOM_PAGE)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(CUSTOM_PAGE).withName(app.customPageName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem customPage = MenuEditorItem.getItem(before, CUSTOM_PAGE);
        app.menuEditor().deleteItem(customPage);
        assertEquals(app.menuEditor().itemsCount(), before.size() - 1);
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(customPage)));
    }
}
