package de.hhbk.jahresprojekt.views.components;

import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.views.modules.autofetch.OnObjectChangedListener;
import de.hhbk.jahresprojekt.views.modules.view.DetailForm;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class SelectDialog<T>  {
    Class<T> tClass;
    OnObjectChangedListener<T> onObjectChangedListener;

    public SelectDialog(Class<T> tClass) {
        this.tClass = tClass;
    }

    public WorkbenchDialog getDialog() throws Exception {
        ListView<T> listView = new ListView<>();
        Repository<T> repository = new Repository<>(tClass);
        listView.setItems(FXCollections.observableArrayList(repository.findAll()));
        return WorkbenchDialog.builder("Details", listView, ButtonType.OK).maximized(false).onResult(buttonType -> {
            onObjectChangedListener.changed(listView.getSelectionModel().getSelectedItem());
        }).build();
    }

    public void setOnObjectChangedListener(OnObjectChangedListener<T> onObjectChangedListener) {
        this.onObjectChangedListener = onObjectChangedListener;
    }
}
