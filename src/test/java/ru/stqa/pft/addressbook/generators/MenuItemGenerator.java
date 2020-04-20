package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.MenuEditorItem;
import ru.stqa.pft.addressbook.model.MenuEditorItem.Type;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.*;

public class MenuItemGenerator {

    @Parameter (names = "-t1", description = "First type of items")
    public String type1;

    @Parameter (names = "-c1", description = "Amount of items with first type")
    public int count1;

    @Parameter (names = "-f", description = "Target file")
    public String file;



    public static void main(String[] args) throws IOException {
        MenuItemGenerator generator = new MenuItemGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<MenuEditorItem> items = generateItems(type1, count1);
        save(items, new File(file));
    }

    private void save(List<MenuEditorItem> items, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(MenuEditorItem item : items) {
            writer.write(String.format("%s;%s\n", item.getType(), item.getName()));
        }
        writer.close();
    }

    private List<MenuEditorItem> generateItems(String type1, int count1) {
        Type type = getType(type1);
        if(type == STORE || type == BLOG) { count1 = 1; }
        List<MenuEditorItem> items = new ArrayList<>();
        for (int i = 0; i < count1; i++) {
            items.add(new MenuEditorItem().withType(type).withName(String.format("Test " + type1 + " %s (*Selenium*)", i)));
        }
        return items;
    }

    private static Type getType(String type) {
        Type t;
        switch (type) {
            case "custom page": t = CUSTOM_PAGE; break;
            case "gallery": t = GALLERY; break;
            case "collection": t = COLLECTION; break;
            case "blog": t = BLOG; break;
            case "blog post": t = BLOG_POST; break;
            case "external link": t = EXTERNAL_LINK; break;
            case "proofing project": t = PROOFING_PROJECT; break;
            case "submenu": t = SUBMENU; break;
            case "store": t = STORE; break;
            case "store product": t = STORE_PRODUCT; break;
            default: t = null;
        }
        return t;
    }
}
