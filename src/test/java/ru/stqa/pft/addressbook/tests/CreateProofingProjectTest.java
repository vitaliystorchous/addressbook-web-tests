package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.PROOFING_PROJECT;

public class CreateProofingProjectTest extends TestBase {

    @Test
    public void test() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem proofingProject = new MenuEditorItem().withType(PROOFING_PROJECT).withName(app.proofingProjectName);
        app.menuEditor().createItem(proofingProject);
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size() + 1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(proofingProject.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt()))));
    }

}
