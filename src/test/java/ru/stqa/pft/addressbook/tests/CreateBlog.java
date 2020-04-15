package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.util.Comparator;
import java.util.List;

public class CreateBlog extends TestBase {

    @Test (enabled = false)
    public void theTest() {
        app.goTo().pagesPage();
        List<MenuEditorItem> before = app.menuEditor().itemsList();
        if(! app.menuEditor().isBlogPresent(before)) {
            MenuEditorItem item = new MenuEditorItem().withType(Type.BLOG).withName(app.blogName);
            app.menuEditor().createItem(item);
            List<MenuEditorItem> after = app.menuEditor().itemsList();
            Assert.assertEquals(after.size(), before.size() + 1);

            before.add(item);
            Comparator<? super MenuEditorItem> byId = (i1, i2) -> Integer.compare(i1.getDataId(), i2.getDataId());
            before.sort(byId);
            after.sort(byId);
            Assert.assertEquals(after, before);
        } else {
            System.out.println("Blog is already present");
        }
    }

}
