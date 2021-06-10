package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class PaymentReceived {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;
    @TableField
    public Timestamp date;
    @ManyToOne
    @TableField
    public RentalObject rentalObject;
    @TableField
    public int amount;
    @ManyToOne
    @TableField
    public Tenant tenant;


    public PaymentReceived() {
    }

    public PaymentReceived(Long id, Date date, RentalObject rentalObject, int amount, Tenant tenant) {
        this.id = id;
        this.date = new Timestamp(date.getTime());
        this.rentalObject = rentalObject;
        this.amount = amount;
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new Timestamp(date.getTime());
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

    @Override
    public String toString() {
        return String.join(", ", String.valueOf(id), getDate().toString(), getTenant().toString(), String.valueOf(getAmount()));
    }
}
