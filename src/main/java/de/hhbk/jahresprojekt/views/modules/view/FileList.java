package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.database.files.FileHandler;
import de.hhbk.jahresprojekt.model.File;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * @author Frederick Hafemann
 */
public class FileList extends ObjectList<File>{

    protected Button show, send;

    public FileList(List<File> objectList){
        super(objectList, File.class);


        add.setOnMouseClicked(mouseEvent -> {
            try {
                File file = FileHandler.getInstance().addNewVersion();
                if(file != null) objectList.add(file);
                setItems();
            } catch (IOException e) {
                e.printStackTrace();
            }
            onChangeListener.changed(null);
        });


        show = new Button("Open");
        show.setOnAction(actionEvent -> new Thread(() -> {
            try {
                Desktop.getDesktop().open(new java.io.File(items.getSelectionModel().getSelectedItem().getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start());
        actions.getChildren().add(show);

        remove.setOnMouseClicked(mouseEvent -> {
            File file = items.getSelectionModel().getSelectedItem();
            if(file != null){
                objectList.remove(file);
                try {
                    FileHandler.getInstance().delete(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setItems();
            }
            onChangeListener.changed(null);
        });

        send = new Button("Senden");
        actions.getChildren().add(send);
    }
}
