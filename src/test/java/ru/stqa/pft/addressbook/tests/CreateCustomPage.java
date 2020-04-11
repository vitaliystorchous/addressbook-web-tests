package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.List;


public class CreateCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        app.getMenuEditorHelper().createMenuEditorItem(new MenuEditorItem(Type.CUSTOM_PAGE, app.customPageName));
        app.getPageEditorHelper().waitPageEditorOpened();
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
