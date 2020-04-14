package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.MenuEditorHelper;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.List;

public class SetCustomPageAsHomepage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        app.getMenuEditorHelper().setSelectedPageAsHomepage(app.customPageName);
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        app.pause(3);
        Assert.assertEquals(after, before);
    }
}
