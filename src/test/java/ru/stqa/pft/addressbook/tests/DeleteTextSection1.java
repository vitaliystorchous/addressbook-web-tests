package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteTextSection1 extends TestBase {

    @Test
    public void theTest() {
        app.getNavigationHelper().goToPagesPage();
        app.getMenuEditorHelper().checkCustomPagePresence(app.customPageName);
        app.getMenuEditorHelper().openSelectedPageInPageEditor(app.customPageName);
        app.getPageEditorHelper().checkTextSection1Presence();
        // последнем шаге метода deleteFirstTextSection() тест упадет, 
        // потому что кнопка не успевает стать кликабельной к моменту, кодгда селениум делает клик по ней
        // нужно добавить ожидание момента, когда кнопка станет кликабельной. в медленном темпе (в дебагере) тест работае нормально, и бывает через раз срабатывает
        app.getPageEditorHelper().deleteFirstTextSection();
    }

}
