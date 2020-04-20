package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.BLOG;

//этот тест нужно доработать в отдельном порядке (сейчас нету смысла тратить на него много времени так как блог нельзя удалить)
public class CreateBlogTest extends TestBase {

    @Test (enabled = false)
    public void test() {
        app.goTo().pagesPage();
        Set<MenuEditorItem> before = app.menuEditor().allItems();
        if(! app.menuEditor().isBlogPresent(before)) {
            MenuEditorItem blog = new MenuEditorItem().withType(BLOG).withName(app.blogName);
            app.menuEditor().createItem(blog);
            Set<MenuEditorItem> after = app.menuEditor().allItems();
            Assert.assertEquals(after.size(), before.size() + 1);

            blog.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt());
            before.add(blog);
            Assert.assertEquals(after, before);
        } else {
            System.out.println("Blog is already present");
        }
    }

}
