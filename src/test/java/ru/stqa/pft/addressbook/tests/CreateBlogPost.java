package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;

public class CreateBlogPost extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        if(! app.getMenuEditorHelper().isBlogPresent(before)) {
            MenuEditorItem blog = new MenuEditorItem(Type.BLOG, app.blogName);
            app.getMenuEditorHelper().createMenuEditorItem(blog);
            app.getBlogEditorHelper().waitBlogEditorOpened();
            app.getNavigationHelper().goToPagesPage();
            before.add(blog);
        }
        app.getMenuEditorHelper().createMenuEditorItem(new MenuEditorItem(Type.BLOG_POST, app.blogPostName));
        app.getPageEditorHelper().waitPageEditorOpened();
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}
