package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @TableField
    private Long id;
    @ManyToOne
    @TableField
    private Person recipient;
    @TableField(label = "Datum")
    private Timestamp date;
    @OneToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> itemList = new ArrayList<>();
    @TableField(label = "Bezahlt")
    private boolean settled;

    public Invoice() {
    }

    public Invoice(Long id, Person recipient, Date date, List<Item> itemList, boolean settled) {
        this.id = id;
        this.recipient = recipient;
        this.date = new Timestamp(date.getTime());
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
        this.date = new Timestamp(date.getTime());
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Rechnung für "
                + ((recipient == null)?" / ":(recipient.getFirstName() + " " + recipient.getLastName()))
                + " vom " + (date == null ? " / " : date.toString()) + " ("
                + (settled ? "Abgeschlossen" : "Offen" + ")");
    }
}
