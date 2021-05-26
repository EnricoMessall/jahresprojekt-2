package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.model.Person;

import java.util.Date;
import java.util.List;

public final class InvoiceBuilder {
    private int id;
    private Person recipient;
    private Date date;
    private List<Item> itemList;

    private InvoiceBuilder() {
    }

    public static InvoiceBuilder anInvoice() {
        return new InvoiceBuilder();
    }

    public InvoiceBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public InvoiceBuilder withRecipient(Person recipient) {
        this.recipient = recipient;
        return this;
    }

    public InvoiceBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public InvoiceBuilder withItemList(List<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    public Invoice build() {
        return new Invoice(id, recipient, date, itemList);
    }
}