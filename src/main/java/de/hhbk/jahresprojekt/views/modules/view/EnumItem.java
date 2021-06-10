package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.views.components.Dialog;
import de.hhbk.jahresprojekt.views.components.DialogContainer;
import de.hhbk.jahresprojekt.views.components.Error;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

/**
 * @author Enrico Messall
 */
public class EnumItem<T extends Enum<T>> extends Button {
    private OnObjectChangedListener<T> onObjectChangedListener;

    public EnumItem(T object, Class<T > tClass) {
        super(object==null ? "Nichts ausgewählt" : object.toString());

        setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                try {
                    Dialog<T> dialog = DialogContainer.get(tClass);
                    System.out.println(tClass);
                    dialog.setOnObjectChangedListener(nValue -> {
                        setText(nValue==null? "Nichts ausgewählt" : nValue.toString());
                        onObjectChangedListener.changed(nValue);
                    });
                    WorkbenchHolder.getInstance().getWorkbench().showDialog(dialog.getDialog());
                } catch (Exception e) {
                    new Error(e.getMessage());
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
