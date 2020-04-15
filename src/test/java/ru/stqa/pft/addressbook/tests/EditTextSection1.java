package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class EditTextSection1 extends TestBase {

    @Test (enabled = false)
    public void theTest() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName);
        app.menuEditor().openSelectedPageInPageEditor(app.customPageName);
        app.getPageEditorHelper().checkTextSection1Presence();
        app.getPageEditorHelper().editTextOfFirstTextSection();
    }
}
