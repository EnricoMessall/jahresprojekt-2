package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.views.components.AddressDialog;
import de.hhbk.jahresprojekt.views.components.Dialog;
import de.hhbk.jahresprojekt.views.components.ItemDialog;
import de.hhbk.jahresprojekt.views.components.SelectDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.OnObjectChangedListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ObjectList<T> extends VBox {
    private List<T> objectList;
    private ListView<T> items;
    private Class<T> tClass;

    public ObjectList(List<T> objectList, Class<T> tClass){
        this.tClass = tClass;
        this.objectList = objectList;
        HBox actions = new HBox();
        Button add = new Button("Add");
        Button remove = new Button("Remove");
        actions.setPadding(new Insets(5, 0, 5, 0));
        actions.setSpacing(5);
        actions.getChildren().add(remove);
        actions.getChildren().add(add);

        items = new ListView<>();

        remove.setOnMouseClicked(mouseEvent -> {
            T object = items.getSelectionModel().getSelectedItem();
            if(object != null){
                System.out.println("remove: " + object.toString());
                objectList.remove(object);
                setItems();
            }
        });

        add.setOnMouseClicked(mouseEvent -> {
            Dialog<T> dialog = switch (tClass.getName()){
                case "de.hhbk.jahresprojekt.model.Item" -> new ItemDialog();
                case "de.hhbk.jahresprojekt.model.Address" -> new AddressDialog();
                default -> new SelectDialog<T>(tClass);
            };
            dialog.setOnObjectChangedListener(nValue -> {
                if(nValue != null) {
                    objectList.add(nValue);
                    setItems();
                }
            });
            try {
                WorkbenchHolder.getInstance().getWorkbench().showDialog(dialog.getDialog());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("add new: " + tClass.getName());
        });

        getChildren().add(actions);
        getChildren().add(items);

        setItems();
    }

    private void setItems(){
        items.setItems(FXCollections.observableArrayList(objectList));
    }
}
