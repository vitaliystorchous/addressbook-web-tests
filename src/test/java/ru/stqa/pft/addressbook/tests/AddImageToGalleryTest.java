package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.io.File;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class AddImageToGalleryTest extends TestBase {

    @BeforeMethod
    public void esurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(GALLERY)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(GALLERY).withName(app.galleryName));
        }
    }

    @Test
    public void test() {
        Items items = app.menuEditor().allItems();
        MenuEditorItem gallery = MenuEditorItem.getItem(items, GALLERY);
        app.menuEditor().openItemInPageEditor(gallery);
        File image = new File("src/test/resources/testImage.jpg");
        app.pageEditor().gallery().addImage(image);
    }
}
