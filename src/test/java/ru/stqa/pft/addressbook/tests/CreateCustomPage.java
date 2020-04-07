package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CreateCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().createCustomPage(app.customPageName);
        app.getNavigationHelper().goToPagesPage();
        Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(app.customPageName));
    }

}
