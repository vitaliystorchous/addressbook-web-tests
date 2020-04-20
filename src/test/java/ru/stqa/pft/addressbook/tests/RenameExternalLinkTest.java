package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.EXTERNAL_LINK;

public class RenameExternalLinkTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(EXTERNAL_LINK, app.externalLinkName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(EXTERNAL_LINK).withName(app.externalLinkName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem externalLinkToRename = MenuEditorItem.getItem(before, EXTERNAL_LINK, app.externalLinkName);
        MenuEditorItem renamedExternalLink = externalLinkToRename.withName("Renamed test external Link (*Selenium*)");
        app.menuEditor().renameItem(externalLinkToRename);
        assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(externalLinkToRename).withAdded(renamedExternalLink)));
    }
}
