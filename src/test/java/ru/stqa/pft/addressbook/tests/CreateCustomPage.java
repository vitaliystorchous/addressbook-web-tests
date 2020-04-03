package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class CreateCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.createCustomPage(app.customPageName);
        app.goBackToPagesFromPageEditor();
    }

}
