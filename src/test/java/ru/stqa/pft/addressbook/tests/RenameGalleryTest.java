package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.GALLERY;

public class RenameGalleryTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(GALLERY, app.galleryName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(GALLERY).withName(app.galleryName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem galleryToRename = MenuEditorItem.getItem(before, GALLERY, app.galleryName);
        MenuEditorItem renamedGallery = galleryToRename.withName("Renamed test gallery (*Selenium*)");
        app.menuEditor().renameItem(galleryToRename);
        Assert.assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(galleryToRename).withAdded(renamedGallery)));
    }
}
