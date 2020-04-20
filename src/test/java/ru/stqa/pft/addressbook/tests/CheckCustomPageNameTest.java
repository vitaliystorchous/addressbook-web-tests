package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.getItem;

public class CheckCustomPageNameTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(CUSTOM_PAGE)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(CUSTOM_PAGE).withName(app.customPageName));
        }
    }

    @Test
    public void test() {
        Items items = app.menuEditor().allItems();
        MenuEditorItem customPage = getItem(items, CUSTOM_PAGE);
        app.menuEditor().openItemInPageEditor(customPage);
        assertThat(app.pageEditor().pageName(), equalTo(customPage.getName()));
    }
}
