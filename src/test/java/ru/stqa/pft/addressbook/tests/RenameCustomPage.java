package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class RenameCustomPage extends TestBase {

    String newPageName = "Renamed " + app.customPageName;

    @Test
    public void testRenameCustomPage() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        for(int i = 0; i < before.size() - 1; i++) {
            if(before.get(i).getType() == MenuEditorItem.Type.CUSTOM_PAGE && before.get(i).getName().equals(app.customPageName)) {
                before.add(new MenuEditorItem(before.get(i).getDataId(), MenuEditorItem.Type.CUSTOM_PAGE, newPageName));
                before.remove(i);
                break;
            }
        }
        app.getMenuEditorHelper().renameSelectedPage(app.customPageName, newPageName);
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
