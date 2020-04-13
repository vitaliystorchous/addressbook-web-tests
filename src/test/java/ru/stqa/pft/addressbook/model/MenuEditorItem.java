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

    int dataId;
    Type type;
    String name;
    boolean homepage;
    boolean inMenu;
    //нужно создать еще подклас, наследник данного класа, который будет содержать дополнительные атрибуты для элемента External link


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuEditorItem item = (MenuEditorItem) o;

        if (type != item.type) return false;
        return name != null ? name.equals(item.name) : item.name == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public MenuEditorItem(Type type, String name) {
        this.dataId = Integer.MAX_VALUE;
        this.type = type;
        this.name = name;
        this.homepage = false;
        switch (type) {
            case GALLERY: case CUSTOM_PAGE: case COLLECTION: case BLOG: case PROOFING_PROJECT: case STORE:
                this.inMenu = false;
                break;

            case EXTERNAL_LINK: case SUBMENU:
                this.inMenu = true;
                break;
        }
    }

    public MenuEditorItem(int dataId, Type type, String name) {
        this.dataId = dataId;
        this.type = type;
        this.name = name;
        this.homepage = false;
        switch (type) {
            case GALLERY: case CUSTOM_PAGE: case COLLECTION: case BLOG: case PROOFING_PROJECT: case STORE:
                this.inMenu = false;
                break;

            case EXTERNAL_LINK: case SUBMENU:
                this.inMenu = true;
                break;
        }
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public Type getType() {
        return this.type;
    }

    public String getName() {
        return  this.name;
    }

    public void setHomepage(boolean value) {
        this.homepage = value;
    }

    public boolean isHomepage() {
        return this.homepage;
    }

    public void setInMenu(boolean value) {
        this.inMenu = value;
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

}
