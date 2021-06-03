package de.hhbk.jahresprojekt.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {

    private Button login;

    private Button usermanagement;
    @FXML
    private Button finance, tenant;

    @FXML
    protected void initialize(){
        finance.setOnMouseClicked(event -> {
            ViewManager.getInstance().showView(ViewManager.view.FINANCE);
        });

        tenant.setOnMouseClicked(event -> {
            ViewManager.getInstance().showView(ViewManager.view.TENANT);
        });
    }

    public void loadLogin () {

    }

    public void loadUsermanagement () {

    }

    public void loadFinanzverwaltung () {

    }
}
