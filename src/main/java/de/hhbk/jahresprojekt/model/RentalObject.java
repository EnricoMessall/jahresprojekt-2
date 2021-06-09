package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.database.repositories.RentalObjectRepository;
import de.hhbk.jahresprojekt.views.annotations.TableField;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class RentalObject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @TableField(label = "Nummer")
    private String objectNumber;
    private String objectDescription;
    @TableField(label = "Typ")
    private RentalType rentalType;
    @TableField(label = "Kommerziell")
    private boolean commercial;
    @OneToOne
    @TableField(label = "Adresse")
    private Address address;
    @TableField(label = "Raum")
    private int livingSpace;
    @TableField(label = "Quadratmeter Preis")
    private int squareMeterPrice;
    private int additionalCosts;
    private String notes;
    @OneToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RentalObject> subObjects = new ArrayList<>();
    @ManyToOne
    @TableField(label = "Mieter")
    private Tenant tenant;
    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Tenant> contacts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Invoice> invoices = new ArrayList<>();
    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Document> documents = new ArrayList<>();

    public void adddocuments(Document document){
        document.getRelatedRentalObjects().add(this);
        RepositoryContainer.get(DocumentRepository.class).save(document);
    }

    public void removedocuments(Document document){
        document.getRelatedRentalObjects().remove(this);
        RepositoryContainer.get(DocumentRepository.class).save(document);
    }

    public RentalObject(){}

    public RentalObject(Long id, String objectNumber, String objectDescription, RentalType rentalType, boolean commercial, Address address, int livingSpace, int squareMeterPrice, int additionalCosts, String notes, List<RentalObject> subObjects, Tenant tenant, List<Tenant> contacts, List<Invoice> invoices, List<Document> documents) {
        this.id = id;
        this.objectNumber = objectNumber;
        this.objectDescription = objectDescription;
        this.rentalType = rentalType;
        this.commercial = commercial;
        this.address = address;
        this.livingSpace = livingSpace;
        this.squareMeterPrice = squareMeterPrice;
        this.additionalCosts = additionalCosts;
        this.notes = notes;
        this.subObjects = subObjects;
        this.tenant = tenant;
        this.contacts = contacts;
        this.invoices = invoices;
        this.documents = documents;
    }

    public Long getId() {
        return id;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public int getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(int livingSpace) {
        this.livingSpace = livingSpace;
    }

    public int getSquareMeterPrice() {
        return squareMeterPrice;
    }

    public void setSquareMeterPrice(int squareMeterPrice) {
        this.squareMeterPrice = squareMeterPrice;
    }

    public int getAdditionalCosts() {
        return additionalCosts;
    }

    public void setAdditionalCosts(int additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<RentalObject> getSubObjects() {
        return subObjects;
    }

    public void setSubObjects(List<RentalObject> subObjects) {
        this.subObjects = subObjects;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public List<Tenant> getContacts() {
        return contacts;
    }

    public void setContacts(List<Tenant> contacts) {
        this.contacts = contacts;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Objekt: " + objectNumber + " Ort: " + address + " mit Mieter: " + tenant;
    }
}
