package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

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
        MenuEditorItem blogToRename = MenuEditorItem.getItem(before, BLOG);
        MenuEditorItem renamedBlog = blogToRename.withName("Renamed test blog (*Selenium*)");
        app.menuEditor().renameItem(blogToRename);
        assertEquals(app.menuEditor().itemsCount(), before.size());
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before.without(blogToRename).withAdded(renamedBlog)));
    }

    @AfterMethod
    public void returnBackCondition() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem blogToRename = MenuEditorItem.getItem(before, BLOG);
        blogToRename.withName(app.blogName);
        app.menuEditor().renameItem(blogToRename);
        app.pause(2);
    }
}
