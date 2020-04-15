package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.List;

public class DeleteCustomPage extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName);
    }

    @Test
    public void theTest() {
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        app.menuEditor().deleteSelectedPage(app.customPageName); //нужно переделать на deleteItem
        List<MenuEditorItem> after = app.menuEditor().itemsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        for(int i = 0; i <= before.size(); i++) {
            if(before.get(i).getName().equals(app.customPageName)) {
                before.remove(i);
                break;
            }
        }
        Assert.assertEquals(after, before);
        //Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(By.cssSelector(".format-ui-toast-header")));
        // нужно добавить проверку того, что страницы нету в списке menu editor
    }

}
