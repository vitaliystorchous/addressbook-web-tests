package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCustomPageToMenu extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        int before = app.getMenuEditorHelper().getPagesCount();
        app.getMenuEditorHelper().addSelectedPageToMenu(app.customPageName);
        int after = app.getMenuEditorHelper().getPagesCount();
        Assert.assertEquals(after, before);
    }
}
