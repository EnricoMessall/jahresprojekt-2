package de.hhbk.jahresprojekt.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Tenant extends Person{
    private Date tenancyStart;
    private Date getTenancyEnd;
    @ManyToOne
    private Address oldAddress;
    @OneToOne
    private BankAccount bankAccount;
    @OneToMany
    private List<RentalObject> rentalObjects;
    @ManyToMany
    private List<Document> documents;
    private boolean contactOnly;

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

    public Address getOldAdress() {
        return oldAddress;
    }

    public void setOldAdress(Address oldAddress) {
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
}
