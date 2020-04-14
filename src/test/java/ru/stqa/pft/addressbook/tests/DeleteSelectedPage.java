package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.List;

public class DeleteSelectedPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        List<MenuEditorItem> before = app.getMenuEditorHelper().getMenuItemsList();
        // в методе deleteSelectedPage() по индексу нужно сделать проверку что это не блог и не магазин
        // (что под этим индексом находится страница и ее можно удалить)
        app.getMenuEditorHelper().deleteSelectedPage(before.size() - 1);
        List<MenuEditorItem> after = app.getMenuEditorHelper().getMenuItemsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
        //Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(By.cssSelector(".format-ui-toast-header")));
        // нужно добавить проверку того, что страницы нету в списке menu editor
    }

}
