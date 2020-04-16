package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class CreateCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().all();
        MenuEditorItem item = new MenuEditorItem().withType(Type.CUSTOM_PAGE).withName(app.customPageName);
        app.menuEditor().createItem(item);
        Set<MenuEditorItem> after = app.menuEditor().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        item.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt());
        before.add(item);
        Assert.assertEquals(after, before);
    }

}
