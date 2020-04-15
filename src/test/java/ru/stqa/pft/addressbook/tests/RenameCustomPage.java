package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Comparator;
import java.util.List;

public class RenameCustomPage extends TestBase {

    String newPageName = "Renamed " + app.customPageName;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        app.menuEditor().checkCustomPagePresence(app.customPageName); //все checkCustomPagePresence нужно или переписать так, что бы они проверяли наличие определенного item, или просто удалить и написать в самих тестах проверки вручную с помощью метода isElementPresent
    }

    @Test
    public void testRenameCustomPage() {
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        for(int i = 0; i < before.size() - 1; i++) {
            if(before.get(i).getType() == MenuEditorItem.Type.CUSTOM_PAGE && before.get(i).getName().equals(app.customPageName)) {
                before.add(new MenuEditorItem().withDataId(before.get(i).getDataId()).withType(MenuEditorItem.Type.CUSTOM_PAGE).withName(newPageName));
                before.remove(i);
                break;
            }
        }
        app.menuEditor().renameSelectedPage(app.customPageName, newPageName); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        List<MenuEditorItem> after = app.menuEditor().itemsList();
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
