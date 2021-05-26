package de.hhbk.jahresprojekt.model;
//Betrag, Kategorie
public class Item {
    private int id;
    private int value;
    private String comment;

    public Item() {
    }

    public Item(int id, int value, String comment) {
        this.id = id;
        this.value = value;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
