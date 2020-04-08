package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class SetCustomPageAsHomepage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        if(! app.getMenuEditorHelper().isElementPresent(app.customPageName)) {
            app.getMenuEditorHelper().createCustomPage(app.customPageName);
            app.getNavigationHelper().goToPagesPage();
        }
        app.getMenuEditorHelper().setSelectedPageAsHomepage(app.customPageName);
    }
}
