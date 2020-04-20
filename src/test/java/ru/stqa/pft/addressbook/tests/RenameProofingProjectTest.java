package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.PROOFING_PROJECT;

public class RenameProofingProjectTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(PROOFING_PROJECT, app.proofingProjectName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(PROOFING_PROJECT).withName(app.proofingProjectName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem proofingProjectToRename = MenuEditorItem.getItem(before, PROOFING_PROJECT, app.proofingProjectName);
        MenuEditorItem renamedProofingProject = proofingProjectToRename.withName("Renamed test proofing project (*Selenium*)");
        app.menuEditor().renameItem(proofingProjectToRename);
        assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(proofingProjectToRename).withAdded(renamedProofingProject)));
    }
}
