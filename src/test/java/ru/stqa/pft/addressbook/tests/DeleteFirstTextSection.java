package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteFirstTextSection extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().openSelectedPageInPageEditor(app.customPageName);
        app.getPageEditorHelper().deleteFirstTextSection();
    }
}
