package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Set;

public class CreateBlogPostTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(Type.BLOG)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(Type.BLOG).withName(app.blogName));
        }
    }

    @Test
    public void theTest() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        app.menuEditor().createItem(new MenuEditorItem().withType(Type.BLOG_POST).withName(app.blogPostName));
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }

}
