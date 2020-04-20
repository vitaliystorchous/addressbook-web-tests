package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.CUSTOM_PAGE;

public class CreateCustomPageTest extends TestBase {

    @Test
    public void test() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem customPage = new MenuEditorItem().withType(CUSTOM_PAGE).withName(app.customPageName);
        app.menuEditor().createItem(customPage);
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size() + 1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(customPage.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt()))));
    }

}
