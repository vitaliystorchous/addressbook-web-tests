package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RenameCustomPage extends TestBase {

    String newPageName = "Renamed " + app.customPageName;

    @Test
    public void testRenameCustomPage() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        int before = app.getMenuEditorHelper().getPagesCount();
        app.getMenuEditorHelper().renameSelectedPage(app.customPageName, newPageName);
        int after = app.getMenuEditorHelper().getPagesCount();
        Assert.assertEquals(after, before);
        // нужно добавить проверку, этот вариант не работает правильно - Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(newPageName));
        // он находит не тот элемент который нам нужен
    }
}
