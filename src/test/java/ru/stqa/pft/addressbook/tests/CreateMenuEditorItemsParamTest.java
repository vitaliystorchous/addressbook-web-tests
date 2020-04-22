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
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class CreateMenuEditorItemsParamTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validItemsFromXmlFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/menuEditorItems.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<MenuEditorItem> items = gson.fromJson(json, new TypeToken<List<MenuEditorItem>>() {
            }.getType()); //List<MenuEditorItem>.class
            return items.stream().map((i) -> new Object[]{i}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validItemsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/menuEditorItems.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(MenuEditorItem.class);
            List<MenuEditorItem> items = (List<MenuEditorItem>) xstream.fromXML(xml);
            return items.stream().map((i) -> new Object[]{i}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validItemsFromXmlFromJson")
    public void createMenuEditorItem(MenuEditorItem item) {
        app.goTo().pagesPage();
        Items before = app.menuEditor().allItems();

        if(item.getType() == STORE || item.getType() == BLOG) {
            switch (item.getType()) {
                case STORE: {
                    if (app.menuEditor().isItemPresent(STORE)) {
                        System.out.println("Store is already present");
                        return;
                    }
                    }
                case BLOG: {
                    if (app.menuEditor().isItemPresent(BLOG)) {
                        System.out.println("Blog is already present");
                        return;
                    }
                }
            }
        }

        if(item.getType() == STORE_PRODUCT || item.getType() == BLOG_POST) {
            switch (item.getType()) {
                case STORE_PRODUCT: {
                    if (!app.menuEditor().isItemPresent(STORE)) {
                        app.menuEditor().createItem(new MenuEditorItem().withType(STORE).withName("Test store (*Selenium*)"));
                    }
                    break;
                }
                case BLOG_POST: {
                    if (! app.menuEditor().isItemPresent(BLOG)) {
                        app.menuEditor().createItem(new MenuEditorItem().withType(BLOG).withName("Test blog (*Selenium*)"));
                    }
                }
            }
        }

        app.menuEditor().createItem(item);

        switch (item.getType()) {
            case GALLERY: case CUSTOM_PAGE: case STORE: case BLOG: case SUBMENU: case EXTERNAL_LINK: case PROOFING_PROJECT: case COLLECTION: {
                assertThat(app.menuEditor().itemsCount(), equalTo(before.size() + 1));
                break;
            }
            case STORE_PRODUCT: case BLOG_POST: {
                assertThat(app.menuEditor().itemsCount(), equalTo(before.size()));
                break;
            }
        }

        Items after = app.menuEditor().allItems();
        switch (item.getType()) {
            case GALLERY: case CUSTOM_PAGE: case STORE: case BLOG: case EXTERNAL_LINK: case PROOFING_PROJECT: case COLLECTION: {
                assertThat(after, equalTo(
                        before.withAdded(item.withDataId(after.stream().mapToInt((i) -> i.getDataId()).max().getAsInt()))));
                break;
            }
            case SUBMENU: {
                assertThat(after, equalTo(before.withAdded(item.withDataId(MenuEditorItem.getLastCreatedSubmenu(after).getDataId()))));
                break;
            }
            case BLOG_POST: case STORE_PRODUCT: {
                assertThat(after, equalTo(before));
                break;
            }
        }

    }

}
