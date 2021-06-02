package de.hhbk.jahresprojekt;

import de.hhbk.jahresprojekt.view_controller.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Enrico Messall
 */
public class Bootstrap extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Jahresprojekt");
        primaryStage.show();

        ViewManager viewManager = ViewManager.getInstance();
        viewManager.setPrimaryStage(primaryStage);
        viewManager.showView(ViewManager.view.DASHBOARD);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
