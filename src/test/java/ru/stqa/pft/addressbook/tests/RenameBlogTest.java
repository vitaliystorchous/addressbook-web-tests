package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

public class RenameBlogTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(MenuEditorItem.Type.BLOG)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(MenuEditorItem.Type.BLOG).withName(app.blogName));
        }
    }

    @Test
    public void testRenameCustomPage() {
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        MenuEditorItem renamedBlog = MenuEditorItem.getItem(before, MenuEditorItem.Type.BLOG);
        before.remove(renamedBlog);
        before.add(renamedBlog.withName("Renamed " + renamedBlog.getName()));
        app.menuEditor().renameItem(renamedBlog); // здесь нужно переделать метод что бы с помощью него я мог переименовать любую item в menu editor и после этого переименовать метод на renameItem
        Set<MenuEditorItem> after = app.menuEditor().allItems();
        Assert.assertEquals(after.size(), before.size());

        Assert.assertEquals(after, before);
    }

    @AfterMethod
    public void returnBackCondition() {
        Set<MenuEditorItem> items = app.menuEditor().allItems();
        MenuEditorItem renamedBlog = MenuEditorItem.getItem(items, MenuEditorItem.Type.BLOG);
        items.remove(renamedBlog);
        items.add(renamedBlog.withName(app.blogName));
        app.menuEditor().renameItem(renamedBlog);
        app.pause(1);
    }
}
