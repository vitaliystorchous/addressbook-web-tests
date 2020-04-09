package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteSelectedPage extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        int before = app.getMenuEditorHelper().getPagesCount();
        // в методе deleteSelectedPage() по индексу нужно сделать проверку что это не блог и не магазин
        // (что под этим индексом находится страница и ее можно удалить)
        app.getMenuEditorHelper().deleteSelectedPage(before - 1);
        int after = app.getMenuEditorHelper().getPagesCount();
        Assert.assertEquals(after, before - 1);
        //Assert.assertTrue(app.getMenuEditorHelper().isElementPresent(By.cssSelector(".format-ui-toast-header")));
        // нужно добавить проверку того, что страницы нету в списке menu editor
    }

}
