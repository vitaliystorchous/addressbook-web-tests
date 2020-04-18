package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class MoveCustomPageToMenuTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName);
    }

    @Test (enabled = false)
    public void theTest() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        app.menuEditor().addSelectedPageToMenu(app.customPageName); //нужно переделать на addItemToMenu и создать также метод removeItemFormMenu
        app.pause(3);
        Set<MenuEditorItem> after = app.menuEditor().allItems();

        Assert.assertEquals(after, before);
    }
}
