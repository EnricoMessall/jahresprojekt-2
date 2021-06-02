package de.hhbk.jahresprojekt.view_controller;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.model.Tenant;
import de.hhbk.jahresprojekt.view_components.ListObject;
import de.hhbk.jahresprojekt.view_components.ListObjectInvoice;
import de.hhbk.jahresprojekt.view_components.ListObjectPaymentReceived;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.ZoneId;

public class TenanteController {

    private Repository<Tenant> tenantRepository = new Repository<>(Tenant.class);

    @FXML
    TableView maintable;

    @FXML
    protected void initialize(){
        setColumns();
        fetchData();
    }

    private void showTenant(Tenant invoice){
    }

    private void setColumns(){
        TableColumn<Tenant, String> column1 = new TableColumn<>("Vorname");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Tenant, String> column2 = new TableColumn<>("Nachname");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Tenant, String> column3 = new TableColumn<>("Typ");
        column2.setCellValueFactory(new PropertyValueFactory<>("type"));

        maintable.getColumns().add(column1);
        maintable.getColumns().add(column2);
        maintable.getColumns().add(column3);
    }

    private void fetchData(){
        maintable.getItems().setAll(tenantRepository.findAll());
    }
}
