package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.model.Tenant;

import java.util.Date;

public final class PaymentReceivedBuilder {
    private int id;
    private Date date;
    private RentalObject rentalObject;
    private int amount;
    private Tenant tenant;

    private PaymentReceivedBuilder() {
    }

    public static PaymentReceivedBuilder aPaymentReceived() {
        return new PaymentReceivedBuilder();
    }

    public PaymentReceivedBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public PaymentReceivedBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public PaymentReceivedBuilder withRentalObject(RentalObject rentalObject) {
        this.rentalObject = rentalObject;
        return this;
    }

    public PaymentReceivedBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public PaymentReceivedBuilder withTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public PaymentReceived build() {
        return new PaymentReceived(id, date, rentalObject, amount, tenant);
    }
}
