package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @TableField
    private Long id;
    @ManyToOne
    @TableField
    private Person recipient;
    @TableField(label = "Datum")
    private Date date;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> itemList;
    @TableField(label = "Bezahlt")
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

    @Override
    public String toString() {
        return String.join(", ", String.valueOf(id), recipient.toString(), date.toString());
    }
}
