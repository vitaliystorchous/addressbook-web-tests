package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreateProofingProjectTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem proofingProject = new MenuEditorItem().withType(Type.PROOFING_PROJECT).withName(app.proofingProjectName);
        app.menuEditor().createItem(proofingProject);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() + 1);

        proofingProject.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt());
        before.add(proofingProject);
        Assert.assertEquals(after, before);
    }

}
