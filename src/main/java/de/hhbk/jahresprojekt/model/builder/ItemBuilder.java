package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Item;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
public final class ItemBuilder {
    private Long id;
    private int value;
    private String comment;

    private ItemBuilder() {
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public ItemBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Item build() {
        return new Item(id, value, comment);
    }
}
