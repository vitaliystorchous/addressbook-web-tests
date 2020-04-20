package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Items before = app.menuEditor().allItems();
        app.menuEditor().createItem(new MenuEditorItem().withType(Type.BLOG_POST).withName(app.blogPostName));
        assertThat(app.menuEditor().getItemsCount(), equalTo(before.size()));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(before));
    }

}
