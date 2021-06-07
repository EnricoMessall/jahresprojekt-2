package de.hhbk.jahresprojekt.views.components;

import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;

public abstract class Dialog<T> {
    Class<T> tClass;
    OnObjectChangedListener<T> onObjectChangedListener;
    Node view;

    public Dialog(Class<T> tClass) {
        this.tClass = tClass;
        view = initView();
    }

    public void copyFrom(T object){ }

    abstract Node initView();

    abstract T getChangedObject();

    public WorkbenchDialog getDialog() throws Exception {

        return WorkbenchDialog.builder("Details", view, ButtonType.OK).maximized(false).onResult(buttonType -> {
            onObjectChangedListener.changed(getChangedObject());
        }).build();
    }

    public void setOnObjectChangedListener(OnObjectChangedListener<T> onObjectChangedListener) {
        this.onObjectChangedListener = onObjectChangedListener;
    }
}
