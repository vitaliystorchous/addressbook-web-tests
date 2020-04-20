package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.BLOG;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.BLOG_POST;

public class CreateBlogPostTest extends TestBase {

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
        app.menuEditor().createItem(new MenuEditorItem().withType(BLOG_POST).withName(app.blogPostName));
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size()));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before));
    }

}
