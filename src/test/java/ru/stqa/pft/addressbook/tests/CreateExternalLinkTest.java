package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.EXTERNAL_LINK;

public class CreateExternalLinkTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem externalLink = new MenuEditorItem().withType(EXTERNAL_LINK).withName(app.externalLinkName);
        app.menuEditor().createItem(externalLink);
        assertThat(app.menuEditor().getItemsCount(), equalTo(before.size() + 1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(externalLink.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt()))));
    }

}
