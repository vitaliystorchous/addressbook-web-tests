package ru.stqa.pft.addressbook.model;

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

    Type type;
    String name;
    boolean homepage;
    boolean inMenu;
    //нужно создать еще подклас, наследник данного класа, который будет содержать дополнительные атрибуты для элемента External link


    //нужно закончить этот класс для прохождения 42 урока
    public MenuEditorItem(Type type, String name) {
        this.type = type;
        this.name = name;
        this.homepage = false;
        switch (type) {
            case GALLERY: case CUSTOM_PAGE: case COLLECTION: case BLOG: case BLOG_POST: case PROOFING_PROJECT: case STORE: case STORE_PRODUCT:
                this.inMenu = false;
                break;

            case EXTERNAL_LINK: case SUBMENU:
                this.inMenu = true;
                break;
        }
    }

    public Type getType() {
        return this.type;
    }

    public String getName() {
        return  this.name;
    }

    public boolean isHomepage() {
        return this.homepage;
    }
    
    public boolean isInMenu() {
        return this.inMenu;
    }
}
