package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.List;

public class CreateBlog extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        if(! app.getMenuEditorHelper().isBlogPresent(before)) {
            app.getMenuEditorHelper().createMenuEditorItem(new MenuEditorItem(Type.BLOG, app.blogName));
            app.getBlogEditorHelper().waitBlogEditorOpened();
            app.getNavigationHelper().goToPagesPage();
            List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
            Assert.assertEquals(after.size(), before.size() + 1);
        } else {
            System.out.println("Blog is already present");
        }
    }

}
