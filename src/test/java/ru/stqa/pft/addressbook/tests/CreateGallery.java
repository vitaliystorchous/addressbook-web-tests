package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreateGallery extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem gallery = new MenuEditorItem().withType(Type.GALLERY).withName(app.galleryName);
        app.menuEditor().createItem(gallery);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size() + 1);

        gallery.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt());
        before.add(gallery);
        Assert.assertEquals(after, before);
    }

}
