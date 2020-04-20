package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateCustomPageTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem customPage = new MenuEditorItem().withType(Type.CUSTOM_PAGE).withName(app.customPageName);
        app.menuEditor().createItem(customPage);
        Items after = app.menuEditor().allItems();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(customPage.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt()))));
    }

}
