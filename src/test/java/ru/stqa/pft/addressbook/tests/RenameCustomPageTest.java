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
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class RenameCustomPageTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(CUSTOM_PAGE, app.customPageName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(CUSTOM_PAGE).withName(app.customPageName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem renamedCustomPage = MenuEditorItem.getItem(before, CUSTOM_PAGE, app.customPageName);
        MenuEditorItem customPage =
                new MenuEditorItem().withDataId(renamedCustomPage.getDataId()).withType(CUSTOM_PAGE).withName("Renamed test custom page (*Selenium*)");
        app.menuEditor().renameItem(customPage);
        Items after = app.menuEditor().allItems();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(renamedCustomPage).withAdded(customPage)));
    }
}
