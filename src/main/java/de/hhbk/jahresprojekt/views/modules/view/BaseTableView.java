package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.views.components.FilterTable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class BaseTableView<T> extends BorderPane {

    public BaseTableView(Class<T> dataClass, String... labelOrder){
        GridPane pane = new GridPane();
        TextField field = new TextField();
        field.setMinWidth(500);
        pane.add(field, 0, 0);
        pane.add(new Button("Clear"), 1, 0);

        FilterTable<T> table = new FilterTable<>(dataClass)
                .setColumnOrder(labelOrder)
                .populateColumns();
        setTop(pane);
        setCenter(table);
    }
}
