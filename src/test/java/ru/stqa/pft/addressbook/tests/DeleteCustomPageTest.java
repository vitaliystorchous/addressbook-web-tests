package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class DeleteCustomPageTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.CUSTOM_PAGE)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.CUSTOM_PAGE).withName(app.customPageName));
        }
    }

    @Test
    public void theTest() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem customPage = MenuEditorItem.getItem(before, MenuEditorItem.Type.CUSTOM_PAGE);
        app.menuEditor().deleteItem(customPage);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(customPage);
        Assert.assertEquals(after, before);
    }
}
