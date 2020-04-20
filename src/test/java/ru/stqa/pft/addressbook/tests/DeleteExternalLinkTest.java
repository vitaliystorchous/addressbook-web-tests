package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Set;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.EXTERNAL_LINK;

public class DeleteExternalLinkTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().pagesPage();
        if(! app.menuEditor().isItemPresent(EXTERNAL_LINK)) {
            app.menuEditor().createItem(new MenuEditorItem().withType(EXTERNAL_LINK).withName(app.externalLinkName));
        }
    }

    @Test
    public void test() {
        Items before = app.menuEditor().allItems();
        MenuEditorItem externalLink = MenuEditorItem.getItem(before, EXTERNAL_LINK);
        app.menuEditor().deleteItem(externalLink);
        Assert.assertEquals(app.menuEditor().itemsCount(), before.size() - 1);
        Items after = app.menuEditor().allItems();
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(externalLink)));
    }
}
