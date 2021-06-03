package de.hhbk.jahresprojekt.view_components;

import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.Tenant;
import javafx.scene.control.Label;

public class ListObjectInvoice extends ListObject<Invoice>{
    public ListObjectInvoice(Invoice invoice){
        super(invoice);
        getChildren().add(new Label("Rechnung"));
        getChildren().add(new Label(String.valueOf(invoice.getId())));
        getChildren().add(new Label(invoice.getDate().toString()));
        getChildren().add(new Label(invoice.getRecipient().getLastName()));
    }
}
