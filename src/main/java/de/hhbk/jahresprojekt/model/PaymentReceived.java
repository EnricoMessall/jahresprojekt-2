package de.hhbk.jahresprojekt.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class PaymentReceived {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
