package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class CreateSubmenuTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem submenu = new MenuEditorItem().withType(SUBMENU).withName(app.submenuName);
        app.menuEditor().createItem(submenu);
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size() + 1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(submenu.withDataId(MenuEditorItem.getLastCreatedSubmenu(after).getDataId()))));
    }
}
