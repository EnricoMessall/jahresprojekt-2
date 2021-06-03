package de.hhbk.jahresprojekt.view_controller;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.model.builder.InvoiceBuilder;
import de.hhbk.jahresprojekt.model.builder.PersonBuilder;
import de.hhbk.jahresprojekt.view_components.ListObject;
import de.hhbk.jahresprojekt.view_components.ListObjectInvoice;
import de.hhbk.jahresprojekt.view_components.ListObjectPaymentReceived;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinanzeverwaltungController {

    private Repository<Invoice> invoiceRepository = new Repository<>(Invoice.class);
    private Repository<PaymentReceived> paymentReceivedRepository = new Repository<>(PaymentReceived.class);

    @FXML
    ListView<ListObject> mainlist;
    @FXML
    VBox invoiceform;
    @FXML
    TextField iid;
    @FXML
    TextField irecipient;
    @FXML
    DatePicker idate;
    @FXML
    CheckBox isettled;
    @FXML
    ListView<Item> iitemlist;
    @FXML
    VBox prform;
    @FXML
    TextField prid;
    @FXML
    DatePicker prdate;
    @FXML
    TextField prrentalobject;
    @FXML
    TextField pramount;
    @FXML
    TextField prtenant;
    @FXML
    Button addInvoice, addPaymentReceived;

    @FXML
    protected void initialize(){
        invoiceform.setVisible(false);
        prform.setVisible(false);

        mainlist.setOnMouseClicked(event -> {
            invoiceform.setVisible(false);
            prform.setVisible(false);
            ListObject listObject = mainlist.getSelectionModel().getSelectedItem();
            if(listObject.getObject() instanceof Invoice){
                showInvoice((Invoice) listObject.getObject());
            }else if(listObject.getObject() instanceof PaymentReceived){
                showPaymentReceived((PaymentReceived) listObject.getObject());
            }
        });


        fetchData();
    }

    private void showInvoice(Invoice invoice){
        invoiceform.setVisible(true);
        iid.setText(String.valueOf(invoice.getId()));
        irecipient.setText(invoice.getRecipient().getLastName());
        idate.setValue(invoice.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        iitemlist.setItems(FXCollections.observableArrayList(invoice.getItemList()));
    }

    private void showPaymentReceived(PaymentReceived paymentReceived){
        prform.setVisible(true);
        prid.setText(String.valueOf(paymentReceived.getId()));
        prdate.setValue(paymentReceived.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        prrentalobject.setText(paymentReceived.getRentalObject().getObjectNumber());
        pramount.setText(String.valueOf(paymentReceived.getAmount()));
        prtenant.setText(paymentReceived.getTenant().getLastName());
    }

    private void fetchData(){
        mainlist.getItems().clear();
        invoiceRepository.findAll().forEach(invoice -> {
            mainlist.getItems().add(new ListObjectInvoice(invoice));
        });
        paymentReceivedRepository.findAll().forEach(paymentReceived -> {
            mainlist.getItems().add(new ListObjectPaymentReceived(paymentReceived));
        });

    }
}
