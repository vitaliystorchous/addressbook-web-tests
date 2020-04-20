package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class CreateGalleryTest extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem gallery = new MenuEditorItem().withType(GALLERY).withName(app.galleryName);
        app.menuEditor().createItem(gallery);
        assertThat(app.menuEditor().getItemsCount(), equalTo(before.size() + 1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(gallery.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt()))));
    }

}
