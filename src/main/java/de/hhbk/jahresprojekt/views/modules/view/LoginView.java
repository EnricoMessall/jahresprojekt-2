package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.LoginManager;
import de.hhbk.jahresprojekt.views.ViewManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class LoginView extends BorderPane {

    private GridPane pane;
    private final TextField username;
    private final PasswordField password;
    private final Button login;

    public LoginView() {

        pane = new GridPane();
        Text usernametext = new Text("Benutzername");
        username = new TextField();
        Text passwordtext = new Text("Passwort");
        password = new PasswordField();
        login = new Button("Login");
        login.setOnAction(click -> login());

        pane.add(usernametext, 0, 1);
        pane.add(username, 0, 2);
        pane.add(passwordtext, 0, 3);
        pane.add(password, 0, 4);
        pane.add(login, 0, 5);

        setCenter(pane);
    }


    private void login() {
        // TODO if abfrage wieder einkommentieren; nur zu Testzwecken entfernt
        //if(LoginManager.getInstance().login(username.getText(), password.getText())) {
            ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneMainview());
        //} else {
        //    new Alert(Alert.AlertType.ERROR, "Benutzername oder Passwort falsch").showAndWait();
        //}
    }
}

