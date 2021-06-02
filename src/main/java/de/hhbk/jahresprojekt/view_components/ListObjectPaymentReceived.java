package de.hhbk.jahresprojekt.view_components;

import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import javafx.scene.control.Label;

public class ListObjectPaymentReceived extends ListObject<PaymentReceived>{
    public ListObjectPaymentReceived(PaymentReceived paymentReceived){
        super(paymentReceived);
        getChildren().add(new Label("Zahlungseingang"));
        getChildren().add(new Label(String.valueOf(paymentReceived.getId())));
        getChildren().add(new Label(paymentReceived.getDate().toString()));
        getChildren().add(new Label(Integer.toString(paymentReceived.getAmount())));
    }
}
