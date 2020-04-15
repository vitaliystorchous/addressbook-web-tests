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
        app.goTo().pagesPage();
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        if(! app.menuEditor().isBlogPresent(before)) {
            MenuEditorItem blog = new MenuEditorItem().withType(Type.BLOG).withName(app.blogName);
            app.menuEditor().createItem(blog);
            before.add(blog);
        }
        app.menuEditor().createItem(new MenuEditorItem().withType(Type.BLOG_POST).withName(app.blogPostName));
        List<MenuEditorItem> after = app.menuEditor().itemsList();
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}
