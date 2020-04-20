package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Items extends ForwardingSet<MenuEditorItem> {

    private Set<MenuEditorItem> delegate;

    public Items() {
        this.delegate = new HashSet<MenuEditorItem>();
    }

    public Items(Items menuEditorItems) {
        this.delegate = new HashSet<MenuEditorItem>(menuEditorItems.delegate);
    }

    @Override
    protected Set<MenuEditorItem> delegate() {
        return delegate;
    }

    public Items withAdded(MenuEditorItem item) {
        Items items = new Items(this);
        items.add(item);
        return items;
    }

    public Items without(MenuEditorItem item) {
        Items items = new Items(this);
        items.remove(item);
        return items;
    }
}
