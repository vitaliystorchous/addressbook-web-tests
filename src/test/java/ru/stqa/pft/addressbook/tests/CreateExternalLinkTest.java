package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreateExternalLinkTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem externalLink = new MenuEditorItem().withType(Type.EXTERNAL_LINK).withName(app.externalLinkName);
        app.menuEditor().createItem(externalLink);
        app.pause(5); //нужно заменить на waitItemVisible()
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() + 1);

        externalLink.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt());
        before.add(externalLink);
        Assert.assertEquals(after, before);
    }

}
