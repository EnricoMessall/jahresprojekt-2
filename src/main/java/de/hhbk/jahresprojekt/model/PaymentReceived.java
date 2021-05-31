package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.model.Tenant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class PaymentReceived {
    @Id
    private int id;
    private Date date;
    @ManyToOne
    private RentalObject rentalObject;
    private int amount;
    @ManyToOne
    private Tenant tenant;


    public PaymentReceived() {
    }

    public PaymentReceived(int id, Date date, RentalObject rentalObject, int amount, Tenant tenant) {
        this.id = id;
        this.date = date;
        this.rentalObject = rentalObject;
        this.amount = amount;
        this.tenant = tenant;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RentalObject getRentalObject() {
        return rentalObject;
    }

    public void setRentalObject(RentalObject rentalObject) {
        this.rentalObject = rentalObject;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
