package de.hhbk.jahresprojekt.views.components;

import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.views.modules.autofetch.OnObjectChangedListener;
import de.hhbk.jahresprojekt.views.modules.view.DetailForm;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class SelectDialog<T>  extends Dialog<T>{
    ListView<T> listView;

    public SelectDialog(Class<T> tClass) {
        super(tClass);
    }

    @Override
    Node initView() {
        listView = new ListView<>();
        Repository<T> repository = new Repository<>(tClass);
        listView.setItems(FXCollections.observableArrayList(repository.findAll()));
        return listView;
    }

    @Override
    T getChangedObject() {
        return listView.getSelectionModel().getSelectedItem();
    }
}
