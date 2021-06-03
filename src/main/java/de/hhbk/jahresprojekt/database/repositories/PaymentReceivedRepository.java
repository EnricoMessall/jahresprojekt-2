package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.model.User;

public class PaymentReceivedRepository extends Repository<PaymentReceived> {

    public PaymentReceivedRepository() {
        super(PaymentReceived.class);
    }
}
