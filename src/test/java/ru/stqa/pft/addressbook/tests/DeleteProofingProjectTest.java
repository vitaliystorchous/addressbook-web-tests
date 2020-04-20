package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static org.testng.Assert.assertEquals;
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
        Items before = app.menuEditor().allItems();
        MenuEditorItem proofingProject = MenuEditorItem.getItem(before, PROOFING_PROJECT);
        app.menuEditor().deleteItem(proofingProject);
        assertEquals(app.menuEditor().itemsCount(), before.size() - 1);
        Items after = app.menuEditor().allItems();
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(proofingProject)));
    }
}
