package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Invoice;

public class InvoiceRepository extends Repository<Invoice> {

    public InvoiceRepository() {
        super(Invoice.class);
    }
}
