package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Set;

public class CreateBlogPost extends TestBase {

    @Test
    public void theTest() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        if(! app.menuEditor().isBlogPresent(before)) {
            MenuEditorItem blog = new MenuEditorItem().withType(Type.BLOG).withName(app.blogName);
            app.menuEditor().createItem(blog);
            before.add(blog);
        }
        app.menuEditor().createItem(new MenuEditorItem().withType(Type.BLOG_POST).withName(app.blogPostName));
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }

}
