package de.hhbk.jahresprojekt;

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

        Pane dashboard = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        primaryStage.setScene(new Scene(dashboard));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
