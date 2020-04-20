package ru.stqa.pft.addressbook.model;

public class Link {

    public String name;
    public String url;

    public String getName() {
        return name;
    }

    public Link withName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Link withUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return name != null ? name.equals(link.name) : link.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
