package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Address;
import de.hhbk.jahresprojekt.model.BankAccount;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.views.components.*;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.naming.event.ObjectChangeListener;
import java.util.List;

/**
 * @author Frederick Hafemann
 */
public class ObjectList<T> extends VBox {
    protected List<T> objectList;
    protected ListView<T> items;
    protected Class<T> tClass;
    protected Button add, remove;
    protected HBox actions;
    protected OnObjectChangedListener<T> onChangeListener;


    public ObjectList(List<T> objectList, Class<T> tClass){
        this.tClass = tClass;
        this.objectList = objectList;
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
                System.out.println("remove: " + object);
                objectList.remove(object);
                setItems();
            }
            onChangeListener.changed(null);
        });

        add.setOnMouseClicked(mouseEvent -> {
            System.out.println(tClass);
            Dialog<T> dialog = DialogContainer.get(tClass);
            dialog.setOnObjectChangedListener(nValue -> {
                if(nValue != null) {
                    objectList.add(nValue);
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

    protected void setItems(){
        items.setItems(FXCollections.observableArrayList(objectList));
    }
}
