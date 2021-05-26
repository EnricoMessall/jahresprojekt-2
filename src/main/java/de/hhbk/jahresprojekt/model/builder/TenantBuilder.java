package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Adress;
import de.hhbk.jahresprojekt.model.BankAccount;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.model.Tenant;

import java.util.Date;
import java.util.List;

public final class TenantBuilder {
    private int id;
    private String title;
    private String firstName;
    private String lastName;
    private String phoneNumberMobile;
    private String phoneNumberLandline;
    private String email;
    private Date tenancyStart;
    private Date getTenancyEnd;
    private Adress oldAdress;
    private BankAccount bankAccount;
    private List<RentalObject> rentalObjects;

    private TenantBuilder() {
    }

    public static TenantBuilder aTenant() {
        return new TenantBuilder();
    }

    public TenantBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TenantBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TenantBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TenantBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TenantBuilder withPhoneNumberMobile(String phoneNumberMobile) {
        this.phoneNumberMobile = phoneNumberMobile;
        return this;
    }

    public TenantBuilder withPhoneNumberLandline(String phoneNumberLandline) {
        this.phoneNumberLandline = phoneNumberLandline;
        return this;
    }

    public TenantBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TenantBuilder withTenancyStart(Date tenancyStart) {
        this.tenancyStart = tenancyStart;
        return this;
    }

    public TenantBuilder withGetTenancyEnd(Date getTenancyEnd) {
        this.getTenancyEnd = getTenancyEnd;
        return this;
    }

    public TenantBuilder withOldAdress(Adress oldAdress) {
        this.oldAdress = oldAdress;
        return this;
    }

    public TenantBuilder withBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public TenantBuilder withRentalObjects(List<RentalObject> rentalObjects) {
        this.rentalObjects = rentalObjects;
        return this;
    }

    public Tenant build() {
        return new Tenant(id, title, firstName, lastName, phoneNumberMobile, phoneNumberLandline, email, tenancyStart, getTenancyEnd, oldAdress, bankAccount, rentalObjects);
    }
}