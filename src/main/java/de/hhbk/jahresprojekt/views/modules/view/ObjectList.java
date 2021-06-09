package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.views.components.Dialog;
import de.hhbk.jahresprojekt.views.components.DialogContainer;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class ObjectList<T> extends VBox {
    protected List<T> objectList;
    protected ListView<T> items;
    protected Class<T> tClass;
    protected Button add, remove;
    protected HBox actions;
    protected OnObjectChangedListener<T> onChangeListener;
    protected OnObjectChangedListener<T> onRemove;
    protected OnObjectChangedListener<T> onAdd;


    public ObjectList(Object list, Class<T> tClass){
        this.tClass = tClass;
        this.objectList = (List<T>) list;
        if(this.objectList == null) this.objectList = new ArrayList<>();
        actions = new HBox();
        add = new Button("Add");
        remove = new Button("Remove");
        actions.setPadding(new Insets(5, 0, 5, 0));
        actions.setSpacing(5);
        actions.getChildren().add(remove);
        actions.getChildren().add(add);

        items = new ListView<>();

        remove.setOnMouseClicked(mouseEvent -> {
            T object = items.getSelectionModel().getSelectedItem();
            if(object != null){
                if(onRemove != null) onRemove.changed(object);
                objectList.remove(object);
                setItems();
            }
            onChangeListener.changed(null);
        });

        add.setOnMouseClicked(mouseEvent -> {
            Dialog<T> dialog = DialogContainer.get(tClass);
            dialog.setOnObjectChangedListener(nValue -> {
                if(nValue != null) {
                    objectList.add(nValue);
                    if(onAdd != null) onAdd.changed(nValue);
                    setItems();
                    onChangeListener.changed(null);
                }
            });
            try {
                WorkbenchHolder.getInstance().getWorkbench().showDialog(dialog.getDialog());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        getChildren().add(actions);
        getChildren().add(items);

        setItems();
    }

    public void setOnChangeListener(OnObjectChangedListener<T> onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public void setOnRemove(OnObjectChangedListener<T> onRemove) {
        this.onRemove = onRemove;
    }

    public void setOnAdd(OnObjectChangedListener<T> onAdd) {
        this.onAdd = onAdd;
    }

    protected void setItems(){
        items.setItems(FXCollections.observableArrayList(objectList));
    }
}
