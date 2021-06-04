package de.hhbk.jahresprojekt.views.modules.view;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.views.components.*;
import de.hhbk.jahresprojekt.views.modules.autofetch.OnObjectChangedListener;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;


public class ObjectItem<T> extends Button {
    private Class<T> tClass;
    private T object;
    private OnObjectChangedListener<T> onObjectChangedListener;

    public ObjectItem(Class<T> tClass, T object) throws Exception {
        super(object==null?"Nichts ausgewählt":object.toString());
        this.object = object;
        this.tClass = tClass;

        setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                try {
                    Dialog<T> dialog = switch (tClass.getName()){
                        case "de.hhbk.jahresprojekt.model.Item" -> new ItemDialog();
                        case "de.hhbk.jahresprojekt.model.Address" -> new AddressDialog();
                        case "de.hhbk.jahresprojekt.model.BankAccount" -> new BankAccountDialog();
                        default -> new SelectDialog<T>(tClass);
                    };
                    dialog.copyFrom(object);
                    dialog.setOnObjectChangedListener(nValue -> {
                        setText(nValue==null?"Nichts ausgewählt":nValue.toString());
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
