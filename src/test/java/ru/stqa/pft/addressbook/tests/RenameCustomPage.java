package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class RenameCustomPage extends TestBase {

    @Test
    public void testRenameCustomPage() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().clickMoreOptions(app.pageName);
        app.getMenuEditorHelper().renameSelectedPage();
    }
}
