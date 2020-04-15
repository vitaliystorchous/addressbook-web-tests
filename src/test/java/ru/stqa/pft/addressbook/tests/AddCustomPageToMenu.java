package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Comparator;
import java.util.List;

public class AddCustomPageToMenu extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName);
    }

    @Test (enabled = false)
    public void theTest() {
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        app.menuEditor().addSelectedPageToMenu(app.customPageName); //нужно переделать на addItemToMenu и создать также метод removeItemFormMenu
        app.pause(3);
        List<MenuEditorItem> after = app.menuEditor().itemsList();

        Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
