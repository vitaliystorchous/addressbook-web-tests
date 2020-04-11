package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        int before = app.getMenuEditorHelper().getPagesCount();
        app.getMenuEditorHelper().deleteSelectedPage(app.customPageName);
        int after = app.getMenuEditorHelper().getPagesCount();
        Assert.assertEquals(after, before - 1);
        //Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(By.cssSelector(".format-ui-toast-header")));
        // нужно добавить проверку того, что страницы нету в списке menu editor
    }

}
