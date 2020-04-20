package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.BLOG;

public class RenameBlogTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(BLOG)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(BLOG).withName(app.blogName));
        }
    }

    @Test
    public void test() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedBlog = MenuEditorItem.getItem(before, BLOG);
        before.remove(renamedBlog);
        before.add(renamedBlog.withName("Renamed test blog (*Selenium*)"));
        app.menuEditor().renameItem(renamedBlog);
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }

    @AfterMethod
    public void returnBackCondition() {
        Set<MenuEditorItem> items = app.menuEditor().allItems();
        MenuEditorItem renamedBlog = MenuEditorItem.getItem(items, BLOG);
        items.remove(renamedBlog);
        items.add(renamedBlog.withName(app.blogName));
        app.menuEditor().renameItem(renamedBlog);
        app.pause(1);
    }
}
