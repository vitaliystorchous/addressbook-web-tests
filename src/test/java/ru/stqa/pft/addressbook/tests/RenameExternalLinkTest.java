package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameExternalLinkTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.EXTERNAL_LINK)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.EXTERNAL_LINK).withName(app.externalLinkName));
        }
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedExternalLink = MenuEditorItem.getItem(before, MenuEditorItem.Type.EXTERNAL_LINK);
        before.remove(renamedExternalLink);
        before.add(renamedExternalLink.withName("Renamed " + renamedExternalLink.getName()));
        app.menuEditor().renameItem(renamedExternalLink); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
