package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class EditTextSection1 extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        app.getMenuEditorHelper().openSelectedPageInPageEditor(app.customPageName);
        app.getPageEditorHelper().checkTextSection1Presence();
        app.getPageEditorHelper().editTextOfFirstTextSection();
    }
}
