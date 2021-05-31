package de.hhbk.jahresprojekt.model;
//Betrag, Kategorie
public class Item {
    private Long id;
    private int value;
    private String comment;

    public Item() {
    }

    public Item(Long id, int value, String comment) {
        this.id = id;
        this.value = value;
        this.comment = comment;
    }

    public Long getId() {
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
