package de.hhbk.jahresprojekt.views.components;

import de.hhbk.jahresprojekt.database.Repository;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;

/**
 * @author Frederick Hafemann
 */
public class EnumDialog<T extends Enum<T>>  extends Dialog<T>{
    ListView<T> listView;

    public EnumDialog(Class<T> tClass) {
        super(tClass);
    }

    @Override
    Node initView() {
        listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(tClass.getEnumConstants()));
        return listView;
    }

    @Override
    T getChangedObject() {
        return listView.getSelectionModel().getSelectedItem();
    }
}
