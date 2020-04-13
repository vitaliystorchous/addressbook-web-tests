package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.List;

public class RenameCustomPage extends TestBase {

    String newPageName = "Renamed " + app.customPageName;

    @Test
    public void testRenameCustomPage() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        app.getMenuEditorHelper().renameSelectedPage(app.customPageName, newPageName);
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size());
        // нужно добавить проверку, этот вариант не работает правильно - Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(newPageName));
        // он находит не тот элемент который нам нужен
    }
}
