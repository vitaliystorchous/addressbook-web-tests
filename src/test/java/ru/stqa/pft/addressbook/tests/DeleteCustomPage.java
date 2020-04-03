package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().deleteSelectedPage(app.customPageName);
    }

}
