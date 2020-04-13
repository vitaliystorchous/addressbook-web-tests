package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.List;

public class DeleteCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        app.getMenuEditorHelper().deleteSelectedPage(app.customPageName);
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size() - 1);
        //Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(By.cssSelector(".format-ui-toast-header")));
        // нужно добавить проверку того, что страницы нету в списке menu editor
    }

}
