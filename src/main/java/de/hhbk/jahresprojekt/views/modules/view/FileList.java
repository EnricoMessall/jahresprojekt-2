package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.database.files.FileHandler;
import de.hhbk.jahresprojekt.model.File;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
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
        send.setOnMouseClicked(mouseEvent -> {
            File file = items.getSelectionModel().getSelectedItem();
            if(file == null) return;
            String path = file.getPath();
            try {
                String pathCoded = URLEncoder.encode(path, "UTF-8").replace("+", "%20");


                String outlookCmd = "mailto:beispiel@beispiel.com&Attach=" + path.replaceAll("\\\\", "/");
                try {
                    Desktop.getDesktop().mail(new URI(outlookCmd));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        actions.getChildren().add(send);
    }
}
