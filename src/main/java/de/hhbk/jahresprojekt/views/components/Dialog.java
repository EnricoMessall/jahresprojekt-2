package de.hhbk.jahresprojekt.views.components;

import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.views.modules.autofetch.OnObjectChangedListener;
import de.hhbk.jahresprojekt.views.modules.view.ObjectItem;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

public abstract class Dialog<T> {
    Class<T> tClass;
    OnObjectChangedListener<T> onObjectChangedListener;
    Node view;

    public Dialog(Class<T> tClass) {
        this.tClass = tClass;
        view = initView();
    }

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
