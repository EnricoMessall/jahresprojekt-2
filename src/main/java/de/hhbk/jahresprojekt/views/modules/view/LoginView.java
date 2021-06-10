package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.LoginManager;
import de.hhbk.jahresprojekt.views.ViewManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class LoginView extends BorderPane {

    private GridPane pane;
    private final TextField username;
    private final PasswordField password;
    private final Button login;
    private final Text errorMessage;

    public LoginView() {

        pane = new GridPane();
        Text usernametext = new Text("Benutzername");
        username = new TextField();
        Text passwordtext = new Text("Passwort");
        password = new PasswordField();
        errorMessage = new Text("Benutzername oder Passwort falsch");
        errorMessage.setFill(Color.RED);
        errorMessage.setVisible(false);
        login = new Button("Login");
        login.setOnAction(click -> login());
        login.setPrefWidth(100);

        pane.add(usernametext, 0, 1);
        pane.add(username, 0, 2);
        pane.add(passwordtext, 0, 3);
        pane.add(password, 0, 4);
        pane.add(errorMessage, 0, 5);
        pane.add(login, 0, 6);

        pane.setAlignment(Pos.CENTER);
        pane.setVgap(5.0);

        password.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                login();
            }
        });

        setCenter(pane);
    }


    private void login() {
        if(LoginManager.getInstance().login(username.getText(), password.getText())) {
            ViewManager.getInstance().activateScene(ViewManager.getInstance().getSceneMainview());
        } else {
           errorMessage.setVisible(true);
        }
    }
}

