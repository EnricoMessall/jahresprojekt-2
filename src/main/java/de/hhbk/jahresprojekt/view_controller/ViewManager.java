package de.hhbk.jahresprojekt.view_controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager {
    public enum view{
        DASHBOARD,
        FINANCE,
        TENANT
    }
    private static ViewManager viewManager;
    private Stage primaryStage;
    private Scene dashboard, finance, tenant;

    private void centerWindow(){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    private ViewManager() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        this.dashboard = new Scene(parent, 400, 200);

        parent = FXMLLoader.load(getClass().getResource("finance.fxml"));
        this.finance = new Scene(parent, 1000, 600);

        parent = FXMLLoader.load(getClass().getResource("tenant.fxml"));
        this.tenant = new Scene(parent, 1000, 600);
    }

    public void showView(view view){
        switch (view){
            case DASHBOARD:
                primaryStage.setTitle("Immo");
                primaryStage.setScene(dashboard);
                primaryStage.setOnCloseRequest(e -> {});
                break;

            case FINANCE:
                primaryStage.setTitle("Finanzen");
                primaryStage.setScene(finance);
                primaryStage.setOnCloseRequest(e -> {});
                break;

            case TENANT:
                primaryStage.setTitle("Mieter");
                primaryStage.setScene(tenant);
                primaryStage.setOnCloseRequest(e -> {});
                break;
        }
        primaryStage.show();
        centerWindow();
    }

    public static ViewManager getInstance(){
        if(viewManager == null){
            try {
                viewManager = new ViewManager();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return viewManager;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
