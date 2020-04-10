package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;


public class CreateCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        int before = app.getMenuEditorHelper().getPagesCount();
        app.getMenuEditorHelper().createMenuEditorItem(new MenuEditorItem(MenuEditorItem.Type.CUSTOM_PAGE, app.customPageName));
        app.getNavigationHelper().goToPagesPage();
        int after = app.getMenuEditorHelper().getPagesCount();
        Assert.assertEquals(after, before + 1);
        //Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(app.customPageName));
    }

}
