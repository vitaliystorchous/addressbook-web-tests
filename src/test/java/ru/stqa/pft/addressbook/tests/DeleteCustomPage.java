package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        if(! app.getMenuEditorHelper().isElementPresent(app.customPageName)) {
            app.getMenuEditorHelper().createCustomPage(app.customPageName);
            app.getNavigationHelper().goToPagesPage();
        }
        app.getMenuEditorHelper().deleteSelectedPage(app.customPageName);
        Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(By.cssSelector(".format-ui-toast-header")));
        // нужно добавить проверку того, что страницы нету в списке menu editor
    }

}
