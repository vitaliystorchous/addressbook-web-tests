package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreateSubmenu extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem submenu = new MenuEditorItem().withType(Type.SUBMENU).withName(app.submenuName);
        app.menuEditor().createItem(submenu);
        app.pause(2);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() + 1);

        submenu.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt());
        before.add(submenu);
        Assert.assertEquals(before, after);
    }
}
