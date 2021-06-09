package de.hhbk.jahresprojekt.views.components;

import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import de.hhbk.jahresprojekt.views.modules.view.DetailForm;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;

/**
 * @author Frederick Hafemann
 */
public class DetailDialog<T> {
    T object;
    OnObjectChangedListener<T> onObjectChangedListener;

    public DetailDialog(T object) {
        this.object = object;
    }

    public WorkbenchDialog getDialog() throws Exception {
        DetailForm<T> detailForm = new DetailForm<>(object, onObjectChangedListener);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(detailForm);
        return WorkbenchDialog.builder("Details", scrollPane, ButtonType.APPLY).maximized(true).build();
    }

    public void setOnObjectChangedListener(OnObjectChangedListener<T> onObjectChangedListener) {
        this.onObjectChangedListener = onObjectChangedListener;
    }
}
