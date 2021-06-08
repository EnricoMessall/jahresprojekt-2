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

    /**
     * Read fields from object an put them in the current dialog view
     * @param object Current Object
     */
    public void copyFrom(T object){ }

    /**
     * Creating the current dialog view
     * @return View Content
     */
    abstract Node initView();

    /**
     * Creating new object from given data and save to database
     * @return New Object
     */
    abstract T getChangedObject();

    public WorkbenchDialog getDialog() throws Exception {

        return WorkbenchDialog.builder("Details", view, ButtonType.OK).maximized(false).onResult(buttonType ->
                onObjectChangedListener.changed(getChangedObject())).build();
    }

    public void setOnObjectChangedListener(OnObjectChangedListener<T> onObjectChangedListener) {
        this.onObjectChangedListener = onObjectChangedListener;
    }
}
