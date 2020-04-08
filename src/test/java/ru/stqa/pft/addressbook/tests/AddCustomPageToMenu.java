package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddCustomPageToMenu extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        app.getMenuEditorHelper().addSelectedPageToMenu(app.customPageName);
    }
}
