package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.awt.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class CreateCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        MenuEditorItem item = new MenuEditorItem(Type.CUSTOM_PAGE, app.customPageName);
        app.getMenuEditorHelper().createMenuEditorItem(item);
        app.getPageEditorHelper().waitPageEditorOpened();
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size() + 1);



        item.setDataId(after.stream().max((item1, item2) -> Integer.compare(item1.getDataId(), item2.getDataId())).get().getDataId());
        before.add(item);
        Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
    }

}
