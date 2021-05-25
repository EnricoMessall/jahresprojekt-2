package de.hhbk.jahresprojekt.schnittstellen;

import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.model.RentalObject;

import java.util.List;

public interface InvoiceHandler {
    Invoice createInvoice(RentalObject rentalObject, List<Item> itemList);
    void settleInvoice(Invoice invoice);

}
