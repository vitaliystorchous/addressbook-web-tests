package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddCustomPageToMenu extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        if(! app.getMenuEditorHelper().isElementPresent(app.customPageName)) {
            app.getMenuEditorHelper().createCustomPage(app.customPageName);
            app.getNavigationHelper().goToPagesPage();
        }
        app.getMenuEditorHelper().addSelectedPageToMenu(app.customPageName);
    }
}
