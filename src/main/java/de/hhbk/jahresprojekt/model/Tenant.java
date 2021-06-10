package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.database.repositories.RentalObjectRepository;
import de.hhbk.jahresprojekt.views.annotations.TableField;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class Tenant extends Person{
    @TableField
    private Date tenancyStart;
    @TableField
    private Date getTenancyEnd;
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private Address oldAddress;
    @OneToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private BankAccount bankAccount;
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RentalObject> rentalObjects = new ArrayList<>();
    @ManyToMany()
//    @JoinTable(name = "document_tenants",
//            joinColumns = @JoinColumn(name = "id")
//    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Document> documents = new ArrayList<>();
    private boolean contactOnly;

    public void addrentalObjects(RentalObject rentalObject){
        rentalObject.setTenant(this);
        RepositoryContainer.get(RentalObjectRepository.class).save(rentalObject);
    }
    public void adddocuments(Document document){
        document.getRelatedTenants().add(this);
        RepositoryContainer.get(DocumentRepository.class).save(document);
    }

    public void removerentalObjects(RentalObject rentalObject){
        rentalObject.setTenant(null);
        RepositoryContainer.get(RentalObjectRepository.class).save(rentalObject);
    }
    public void removedocuments(Document document){
        document.getRelatedTenants().remove(this);
        RepositoryContainer.get(DocumentRepository.class).save(document);
    }

    public Tenant() {
    }

    public Tenant(Long id, String title, String firstName, String lastName, String phoneNumberMobile, String phoneNumberLandline, String email, Address address, Date tenancyStart, Date getTenancyEnd, Address oldAddress, BankAccount bankAccount, List<RentalObject> rentalObjects, List<Document> documents, boolean contactOnly) {
        super(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email, address);
        this.tenancyStart = tenancyStart;
        this.getTenancyEnd = getTenancyEnd;
        this.oldAddress = oldAddress;
        this.bankAccount = bankAccount;
        this.rentalObjects = rentalObjects;
        this.documents = documents;
        this.contactOnly = contactOnly;
    }

    public Date getTenancyStart() {
        return tenancyStart;
    }

    public void setTenancyStart(Date tenancyStart) {
        this.tenancyStart = tenancyStart;
    }

    public Date getGetTenancyEnd() {
        return getTenancyEnd;
    }

    public void setGetTenancyEnd(Date getTenancyEnd) {
        this.getTenancyEnd = getTenancyEnd;
    }

    public Address getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(Address oldAddress) {
        this.oldAddress = oldAddress;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<RentalObject> getRentalObjects() {
        return rentalObjects;
    }

    public void setRentalObjects(List<RentalObject> rentalObjects) {
        this.rentalObjects = rentalObjects;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public boolean isContactOnly() {
        return contactOnly;
    }

    public void setContactOnly(boolean contactOnly) {
        this.contactOnly = contactOnly;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " Telefon: " + getPhoneNumberLandline();
    }
}
