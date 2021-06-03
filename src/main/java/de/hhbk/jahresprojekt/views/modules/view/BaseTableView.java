package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.views.components.FilterTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class BaseTableView<T> extends BorderPane {

    private GridPane pane;
    private final TextField search;
    private final Button clearButton;
    private final FilterTable<T> table;
    private List<T> data = new ArrayList<>();

    public BaseTableView(Class<T> dataClass, BiPredicate<T, String> filterCondition, String... labelOrder){
        pane = new GridPane();
        search = new TextField();
        clearButton = new Button("Clear");
        table = new FilterTable<>(dataClass, filterCondition)
                .setColumnOrder(labelOrder)
                .populateColumns();

        search.setPrefWidth(500);

        clearButton.setOnAction(click -> search.setText(""));
        search.setOnAction(this::filterTable);

        pane.add(search, 0, 0);
        pane.add(clearButton, 1, 0);

        setTop(pane);
        setCenter(table);
    }

    public void setData(List<T> data){
        this.data = data;
        filterTable(null);
    }

    private void filterTable(ActionEvent event){
        String value = search.getText();
        ObservableList<T> filteredData = FXCollections.observableArrayList();
        if(value.equals("")) filteredData.addAll(data);
        else {
            data.stream()
                    .filter(object -> table.getFilterCondition().test(object, value))
                    .forEach(filteredData::add);
        }
        table.setItems(filteredData);
        table.refresh();
    }
}
