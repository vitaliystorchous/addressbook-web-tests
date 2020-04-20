package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.valueOf;

public class CreateCustomPageTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validItems() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/menuEditorItems.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(MenuEditorItem.class);
        List<MenuEditorItem> items = (List<MenuEditorItem>) xstream.fromXML(xml);
        return items.stream().map((i) -> new Object[] {i}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "validItems")
    public void test(MenuEditorItem customPage) {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();
        app.menuEditor().createItem(customPage);
        assertThat(app.menuEditor().itemsCount(), equalTo(before.size() + 1));
        Items after = app.menuEditor().allItems();
        assertThat(after, equalTo(
                before.withAdded(customPage.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt()))));
    }

}
