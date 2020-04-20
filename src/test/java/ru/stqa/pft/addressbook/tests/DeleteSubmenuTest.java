package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.SUBMENU;

public class DeleteSubmenuTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(SUBMENU)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(SUBMENU).withName(app.submenuName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem submenu = MenuEditorItem.getItem(before, SUBMENU);
        app.menuEditor().deleteItem(submenu);
        Assert.assertEquals(app.menuEditor().itemsCount(), before.size() - 1);
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(submenu)));
    }
}
