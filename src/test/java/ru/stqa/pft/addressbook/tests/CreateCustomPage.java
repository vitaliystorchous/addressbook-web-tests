package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Set;


public class CreateCustomPage extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem customPage = new MenuEditorItem().withType(Type.CUSTOM_PAGE).withName(app.customPageName);
        app.menuEditor().createItem(customPage);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() + 1);

        customPage.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt());
        before.add(customPage);
        Assert.assertEquals(after, before);
    }

}
