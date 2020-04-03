package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class EditFirstTextSection extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().openSelectedPageInPageEditor(app.customPageName);
        app.getPageEditorHelper().editTextOfFirstTextSection();
    }
}
