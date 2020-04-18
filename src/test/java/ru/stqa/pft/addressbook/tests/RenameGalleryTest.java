package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameGalleryTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.GALLERY, app.galleryName)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.GALLERY).withName(app.galleryName));
        }
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedGallery = MenuEditorItem.getItem(before, MenuEditorItem.Type.GALLERY, app.galleryName);
        before.remove(renamedGallery);
        before.add(renamedGallery.withName("Renamed " + renamedGallery.getName()));
        app.menuEditor().renameItem(renamedGallery); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }
}
