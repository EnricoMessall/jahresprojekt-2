package de.hhbk.jahresprojekt.view_components;

import javafx.scene.layout.HBox;

public abstract class ListObject<T> extends HBox {
    protected T object;
    public ListObject(T object){
        this.object = object;
        setStyle("-fx-padding: 10px; -fx-spacing: 10px;");
    }

    public T getObject(){
        return object;
    }
}
