package de.hhbk.jahresprojekt.model;

import java.util.Date;
import java.util.List;

public class Invoice {
    private int id;
    private Person recipient;
    private Date date;
    private List<Item> itemList;

    public Invoice() {
    }

    public Invoice(int id, Person recipient, Date date, List<Item> itemList) {
        this.id = id;
        this.recipient = recipient;
        this.date = date;
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
