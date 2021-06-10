package de.hhbk.jahresprojekt.views.components;

import de.hhbk.jahresprojekt.database.Repository;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;

/**
 * @author Frederick Hafemann
 */
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
