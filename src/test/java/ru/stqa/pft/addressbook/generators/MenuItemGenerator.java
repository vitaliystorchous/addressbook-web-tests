package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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

    @Parameter (names = "-t2", description = "Second type of items")
    public String type2;

    @Parameter (names = "-c2", description = "Amount of items with second type")
    public int count2;

    @Parameter (names = "-t3", description = "3rd type of items")
    public String type3;

    @Parameter (names = "-c3", description = "Amount of items with 3rd type")
    public int count3;

    @Parameter (names = "-t4", description = "4th type of items")
    public String type4;

    @Parameter (names = "-c4", description = "Amount of items with 4th type")
    public int count4;

    @Parameter (names = "-t5", description = "5th type of items")
    public String type5;

    @Parameter (names = "-c5", description = "Amount of items with 5th type")
    public int count5;

    @Parameter (names = "-t6", description = "6th type of items")
    public String type6;

    @Parameter (names = "-c6", description = "Amount of items with 6th type")
    public int count6;

    @Parameter (names = "-t7", description = "7th type of items")
    public String type7;

    @Parameter (names = "-c7", description = "Amount of items with 7th type")
    public int count7;

    @Parameter (names = "-t8", description = "8th type of items")
    public String type8;

    @Parameter (names = "-c8", description = "Amount of items with 8th type")
    public int count8;

    @Parameter (names = "-t9", description = "9th type of items")
    public String type9;

    @Parameter (names = "-c9", description = "Amount of items with 9th type")
    public int count9;

    @Parameter (names = "-t10", description = "10th type of items")
    public String type10;

    @Parameter (names = "-c10", description = "Amount of items with 10th type")
    public int count10;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;


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
        List<MenuEditorItem> items = new ArrayList<>();
        generateItems(items, type1, count1);
        if(type2 != null && count2 > 0) { generateItems(items, type2, count2); }
        if(type3 != null && count3 > 0) { generateItems(items, type3, count3); }
        if(type4 != null && count4 > 0) { generateItems(items, type4, count4); }
        if(type5 != null && count5 > 0) { generateItems(items, type5, count5); }
        if(type6 != null && count6 > 0) { generateItems(items, type6, count6); }
        if(type7 != null && count7 > 0) { generateItems(items, type7, count7); }
        if(type8 != null && count8 > 0) { generateItems(items, type8, count8); }
        if(type9 != null && count9 > 0) { generateItems(items, type9, count9); }
        if(type10 != null && count10 > 0) { generateItems(items, type10, count10); }

        if (format.equals("csv")) {
            saveAsCsv(items, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(items, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(items, new File(file));
        } else {
            System.out.println("Unrecognized format - " + format);
        }
    }

    private void saveAsJson(List<MenuEditorItem> items, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(items);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<MenuEditorItem> items, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(MenuEditorItem.class);
        String xml = xstream.toXML(items);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<MenuEditorItem> items, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (MenuEditorItem item : items) {
                writer.write(String.format("%s;%s\n", item.getType(), item.getName()));
            }
        }
    }

    private List<MenuEditorItem> generateItems(List<MenuEditorItem> items, String type1, int count1) {
        Type type = getType(type1);
        if(type == STORE || type == BLOG) { count1 = 1; }
        for (int i = 0; i < count1; i++) {
            items.add(new MenuEditorItem().withType(type).withName(String.format("Test " + type1 + " %s (*Selenium*)", i)));
        }
        return items;
    }

    private static Type getType(String type) {
        Type t;
        switch (type.toLowerCase()) {
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
