package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddTextSection1 extends TestBase {

    @Test (enabled = false)
    public void theTest() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName);
        app.menuEditor().openSelectedPageInPageEditor(app.customPageName);
        app.getPageEditorHelper().addFirstTextSection();
    }
}
