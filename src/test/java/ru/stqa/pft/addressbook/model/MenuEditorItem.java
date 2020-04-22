package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("item")
@Entity
@Table (name = "menueditoritems")
public class MenuEditorItem {

    public enum Type {
        GALLERY,
        CUSTOM_PAGE,
        EXTERNAL_LINK,
        SUBMENU,
        COLLECTION,
        BLOG,
        BLOG_POST,
        PROOFING_PROJECT,
        STORE,
        STORE_PRODUCT
    }

    @XStreamOmitField
    @Id
    @Column (name = "dataId")
    int dataId = Integer.MAX_VALUE;
    @Expose
    @Column (name = "type")
    @ColumnTransformer(read = "UPPER(type)")
    @Enumerated(EnumType.STRING)
    Type type;
    @Expose
    @Column (name = "name")
    String name;
    @XStreamOmitField
    @Column (name = "homepage")
    boolean homepage;
    @XStreamOmitField
    @Column (name = "inMenu")
    boolean inMenu;
    //нужно создать еще подклас, наследник данного класа, который будет содержать дополнительные атрибуты для элемента External link


    public int getDataId() {
        return dataId;
    }

    public MenuEditorItem withDataId(int dataId) {
        this.dataId = dataId;
        return this;
    }

    public Type getType() {
        return this.type;
    }

    public MenuEditorItem withType(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuEditorItem item = (MenuEditorItem) o;

        if (dataId != item.dataId) return false;
        if (type != item.type) return false;
        return name != null ? name.equals(item.name) : item.name == null;
    }

    @Override
    public int hashCode() {
        int result = dataId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public String getName() {
        return  this.name;
    }

    public MenuEditorItem withName(String name) {
        this.name = name;
        return this;
    }

    public MenuEditorItem withHomepage(boolean value) {
        this.homepage = value;
        return this;
    }

    public boolean isHomepage() {
        return this.homepage;
    }

    public MenuEditorItem withInMenu(boolean value) {
        this.inMenu = value;
        return this;
    }

    public boolean isInMenu() {
        return this.inMenu;
    }

    @Override
    public String toString() {
        return "MenuEditorItem{" +
                "dataId='" + dataId + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }

    public static MenuEditorItem getItem(Set<MenuEditorItem> set, Type type) {
        for(MenuEditorItem item : set) {
            if(item.getType() == type) {
                return item;
            }
        }
        System.out.println("No such item!");
        return null;
    }

    public static MenuEditorItem getItem(Set<MenuEditorItem> set, Type type, String itemName) {
        for(MenuEditorItem item : set) {
            if(item.getType() == type && item.getName().equals(itemName)) {
                return item;
            }
        }
        System.out.println("No such item!");
        return null;
    }

    public static MenuEditorItem getLastCreatedSubmenu(Set<MenuEditorItem> after) {
        Set<MenuEditorItem> submenus = new HashSet<>();
        for(MenuEditorItem item : after) {
            if(item.getType() == Type.SUBMENU) {
                submenus.add(item);
            }
        }

        int maxId = submenus.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt();
        for(MenuEditorItem item : submenus) {
            if(item.getDataId() == maxId) {
                return item;
            }
        }

        System.out.println("Submenu is not found");
        return null;
    }

}
