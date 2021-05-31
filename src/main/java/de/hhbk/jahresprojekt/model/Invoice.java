package de.hhbk.jahresprojekt.model;

import java.util.Date;
import java.util.List;

public class Invoice {
    private Long id;
    private Person recipient;
    private Date date;
    private List<Item> itemList;
    private boolean settled;

    public Invoice() {
    }

    public Invoice(Long id, Person recipient, Date date, List<Item> itemList, boolean settled) {
        this.id = id;
        this.recipient = recipient;
        this.date = date;
        this.itemList = itemList;
        this.settled = settled;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public Long getId() {
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
