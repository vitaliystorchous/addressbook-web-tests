package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.SUBMENU;

public class RenameSubmenuTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(SUBMENU, app.submenuName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(SUBMENU).withName(app.submenuName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem submenuToRename = MenuEditorItem.getItem(before, SUBMENU, app.submenuName);
        MenuEditorItem renamedSubmenu = submenuToRename.withName("Renamed test submenu (*Selenium*)");
        app.menuEditor().renameItem(submenuToRename);
        assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(submenuToRename).withAdded(renamedSubmenu)));
    }
}
