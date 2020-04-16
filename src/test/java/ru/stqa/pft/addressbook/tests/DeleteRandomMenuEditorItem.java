package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class DeleteRandomMenuEditorItem extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName);
    }

    @Test
    public void theTest() {
        Set<MenuEditorItem> before = app.menuEditor().all();
        MenuEditorItem deletedItem = before.iterator().next();
        app.menuEditor().deleteItem(deletedItem);
        Set<MenuEditorItem> after = app.menuEditor().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedItem);
        Assert.assertEquals(after, before);
        //Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(By.cssSelector(".format-ui-toast-header")));
        // нужно добавить проверку того, что страницы нету в списке menu editor
    }

}