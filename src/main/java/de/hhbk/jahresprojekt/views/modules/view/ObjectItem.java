package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Address;
import de.hhbk.jahresprojekt.model.BankAccount;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.views.components.*;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

/**
 * @author Frederick Hafemann
 */
public class ObjectItem<T> extends Button {
    private OnObjectChangedListener<T> onObjectChangedListener;

    public ObjectItem(T object) {
        super(object==null ? "Nichts ausgewählt" : object.toString());
        if(object == null) return;

        setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                try {
                    Dialog<T> dialog = DialogContainer.get((Class<T>)object.getClass());
                    dialog.copyFrom(object);
                    dialog.setOnObjectChangedListener(nValue -> {
                        setText(nValue==null? "Nichts ausgewählt" : nValue.toString());
                        onObjectChangedListener.changed(nValue);
                    });
                    WorkbenchHolder.getInstance().getWorkbench().showDialog(dialog.getDialog());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                onObjectChangedListener.changed(null);
                setText("Nichts ausgewählt");
            }
        });
    }

    public void setOnObjectChangedListener(OnObjectChangedListener<T> onObjectChangedListener) {
        this.onObjectChangedListener = onObjectChangedListener;
    }
}
