package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddFirstTextSectionToCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        app.getMenuEditorHelper().openSelectedPageInPageEditor(app.customPageName);
        app.getPageEditorHelper().addFirstTextSection();
    }
}
