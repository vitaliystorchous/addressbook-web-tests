package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.PROOFING_PROJECT;

public class DeleteProofingProjectTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(PROOFING_PROJECT)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(PROOFING_PROJECT).withName(app.proofingProjectName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem proofingProject = MenuEditorItem.getItem(before, PROOFING_PROJECT);
        app.menuEditor().deleteItem(proofingProject);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(proofingProject);
        Assert.assertEquals(after, before);
    }
}
