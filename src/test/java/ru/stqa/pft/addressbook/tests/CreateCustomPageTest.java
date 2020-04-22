package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.CUSTOM_PAGE;

public class CreateCustomPageTest extends TestBase {

    @Test
    public void createCustomPage() {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        MenuEditorItem customPage = new MenuEditorItem().withType(CUSTOM_PAGE).withName(app.customPageName);
        app.menuEditor().createItem(customPage);
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size() +1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(customPage.withDataId(after.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt()))));
    }

}
