package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;

public class CreateCollection extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        MenuEditorItem item = new MenuEditorItem().withType(Type.COLLECTION).withName(app.collectionName);
        app.menuEditor().createItem(item);
        List<MenuEditorItem> after = app.menuEditor().itemsList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(item);
        Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}
