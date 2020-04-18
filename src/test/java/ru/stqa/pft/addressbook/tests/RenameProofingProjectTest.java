package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameProofingProjectTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.PROOFING_PROJECT)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.PROOFING_PROJECT).withName(app.proofingProjectName));
        }
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedProofingProject = MenuEditorItem.getItem(before, MenuEditorItem.Type.PROOFING_PROJECT);
        before.remove(renamedProofingProject);
        before.add(renamedProofingProject.withName("Renamed " + renamedProofingProject.getName()));
        app.menuEditor().renameItem(renamedProofingProject); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
