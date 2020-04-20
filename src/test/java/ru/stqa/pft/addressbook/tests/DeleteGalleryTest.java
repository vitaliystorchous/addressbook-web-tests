package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.GALLERY;

public class DeleteGalleryTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(GALLERY)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(GALLERY).withName(app.galleryName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem gallery = MenuEditorItem.getItem(before, GALLERY);
        app.menuEditor().deleteItem(gallery);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(gallery);
        Assert.assertEquals(after, before);
    }
}
