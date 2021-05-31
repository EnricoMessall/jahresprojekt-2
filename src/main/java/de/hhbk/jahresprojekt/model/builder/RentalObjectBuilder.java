package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.*;

import java.util.List;

public final class RentalObjectBuilder {
    private Long id;
    private String objectNumber;
    private String objectDescription;
    private RentalType rentalType;
    private boolean commercial;
    private Adress adress;
    private int livingSpace;
    private int squareMeterPrice;
    private int additionalCosts;
    private String notes;
    private List<RentalObject> subObjects;
    private Tenant tenant;
    private List<Tenant> contacts;
    private List<Invoice> invoices;
    private List<Document> documents;

    private RentalObjectBuilder() {
    }

    public static RentalObjectBuilder aRentalObject() {
        return new RentalObjectBuilder();
    }

    public RentalObjectBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RentalObjectBuilder withObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
        return this;
    }

    public RentalObjectBuilder withObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
        return this;
    }

    public RentalObjectBuilder withRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
        return this;
    }

    public RentalObjectBuilder withCommercial(boolean commercial) {
        this.commercial = commercial;
        return this;
    }

    public RentalObjectBuilder withAdress(Adress adress) {
        this.adress = adress;
        return this;
    }

    public RentalObjectBuilder withLivingSpace(int livingSpace) {
        this.livingSpace = livingSpace;
        return this;
    }

    public RentalObjectBuilder withSquareMeterPrice(int squareMeterPrice) {
        this.squareMeterPrice = squareMeterPrice;
        return this;
    }

    public RentalObjectBuilder withAdditionalCosts(int additionalCosts) {
        this.additionalCosts = additionalCosts;
        return this;
    }

    public RentalObjectBuilder withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public RentalObjectBuilder withSubObjects(List<RentalObject> subObjects) {
        this.subObjects = subObjects;
        return this;
    }

    public RentalObjectBuilder withTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public RentalObjectBuilder withContacts(List<Tenant> contacts) {
        this.contacts = contacts;
        return this;
    }

    public RentalObjectBuilder withInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }

    public RentalObjectBuilder withDocuments(List<Document> documents) {
        this.documents = documents;
        return this;
    }

    public RentalObject build() {
        return new RentalObject(id, objectNumber, objectDescription, rentalType, commercial, adress, livingSpace, squareMeterPrice, additionalCosts, notes, subObjects, tenant, contacts, invoices, documents);
    }
}
