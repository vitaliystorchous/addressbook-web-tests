package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;

public class CreateSubmenu extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        MenuEditorItem item = new MenuEditorItem(Type.SUBMENU, app.submenuName);
        app.getMenuEditorHelper().createMenuEditorItem(item);
        app.pause(2);
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(item);
        Comparator<? super MenuEditorItem> byName = (i1, i2) -> i1.getName().compareTo(i2.getName());
        before.sort(byName);
        after.sort(byName);
        Assert.assertEquals(before, after);
    }
}
