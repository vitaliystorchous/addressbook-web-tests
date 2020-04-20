package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

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
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedGallery = MenuEditorItem.getItem(before, GALLERY, app.galleryName);
        before.remove(renamedGallery);
        before.add(renamedGallery.withName("Renamed test gallery (*Selenium*)"));
        app.menuEditor().renameItem(renamedGallery);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
