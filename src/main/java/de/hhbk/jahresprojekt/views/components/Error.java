package de.hhbk.jahresprojekt.views.components;

import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Error {
    public Error(String message){
        VBox vBox = new VBox();
        Label label = new Label("Ein Fehler ist aufgetreten");
        label.setStyle("-fx-font-size: 20;");
        Label label1 = new Label(message);
        vBox.getChildren().add(label);
        vBox.getChildren().add(label1);
        WorkbenchDialog workbenchDialog = WorkbenchDialog.builder("Details", vBox, ButtonType.APPLY).maximized(true).build();
        WorkbenchHolder.getInstance().getWorkbench().showDialog(workbenchDialog);
    }
}
