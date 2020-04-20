package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameRandomMenuEditorItemTest extends TestBase {

    String newPageName = "Renamed test item (*Selenium*)";

    @BeforeMethod (enabled = false)
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName); //все checkCustomPagePresence нужно или переписать так, что бы они проверяли наличие определенного item, или просто удалить и написать в самих тестах проверки вручную с помощью метода isElementPresent
    }

    @Test (enabled = false)
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedItem = before.iterator().next();
        before.remove(renamedItem);
        before.add(renamedItem.withName(newPageName));
        app.menuEditor().renameItem(renamedItem);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        for(MenuEditorItem i : before) {
            System.out.println(i.toString());
        }

        for(MenuEditorItem i : after) {
            System.out.println(i.toString());
        }
        Assert.assertEquals(after, before);
    }
}
