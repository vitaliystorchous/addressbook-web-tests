package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameCustomPage extends TestBase {

    String newPageName = "Renamed " + app.customPageName;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName); //все checkCustomPagePresence нужно или переписать так, что бы они проверяли наличие определенного item, или просто удалить и написать в самих тестах проверки вручную с помощью метода isElementPresent
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedItem = MenuEditorItem.getItem(before, MenuEditorItem.Type.CUSTOM_PAGE, app.customPageName);
        before.remove(renamedItem);
        before.add(renamedItem.withName(newPageName));
        app.menuEditor().renameItem(renamedItem); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
