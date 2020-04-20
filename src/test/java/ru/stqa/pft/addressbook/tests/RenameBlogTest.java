package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
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
        Items before = app.menuEditor().allItems();
        MenuEditorItem blogBefore = MenuEditorItem.getItem(before, BLOG);
        MenuEditorItem blogAfter = blogBefore.withName("Renamed test blog (*Selenium*)");
        app.menuEditor().renameItem(blogBefore);
        assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(blogBefore).withAdded(blogAfter)));
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
