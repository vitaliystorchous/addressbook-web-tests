package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.List;

public class SetCustomPageAsHomepage extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName);
    }

    @Test
    public void theTest() {
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        app.menuEditor().setSelectedPageAsHomepage(app.customPageName);
        List<MenuEditorItem> after = app.menuEditor().itemsList();
        app.pause(3);
        Assert.assertEquals(after, before);
    }
}
