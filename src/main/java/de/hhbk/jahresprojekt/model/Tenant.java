package de.hhbk.jahresprojekt.model;

import java.util.Date;
import java.util.List;

public class Tenant extends Person{
    private Date tenancyStart;
    private Date getTenancyEnd;
    private Adress oldAdress;
    private BankAccount bankAccount;
    private List<RentalObject> rentalObjects;
    private List<Document> documents;
    private boolean contactOnly;

    public Tenant() {
    }

    public Tenant(int id, String title, String firstName, String lastName, String phoneNumberMobile, String phoneNumberLandline, String email, Date tenancyStart, Date getTenancyEnd, Adress oldAdress, BankAccount bankAccount, List<RentalObject> rentalObjects, List<Document> documents, boolean contactOnly) {
        super(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email);
        this.tenancyStart = tenancyStart;
        this.getTenancyEnd = getTenancyEnd;
        this.oldAdress = oldAdress;
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

    public Adress getOldAdress() {
        return oldAdress;
    }

    public void setOldAdress(Adress oldAdress) {
        this.oldAdress = oldAdress;
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
