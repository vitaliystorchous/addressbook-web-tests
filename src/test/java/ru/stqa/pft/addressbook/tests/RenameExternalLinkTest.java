package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.EXTERNAL_LINK;

public class RenameExternalLinkTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(EXTERNAL_LINK, app.externalLinkName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(EXTERNAL_LINK).withName(app.externalLinkName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedExternalLink = MenuEditorItem.getItem(before, EXTERNAL_LINK, app.externalLinkName);
        before.remove(renamedExternalLink);
        before.add(renamedExternalLink.withName("Renamed test external Link (*Selenium*)"));
        app.menuEditor().renameItem(renamedExternalLink);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}