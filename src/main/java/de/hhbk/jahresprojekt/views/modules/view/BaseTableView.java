package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.views.components.FilterTable;
import de.hhbk.jahresprojekt.views.modules.autofetch.AddListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.function.BiPredicate;

public class BaseTableView<T> extends BorderPane {

    private HBox pane;
    private final TextField search;
    private final Button clearButton;
    private final Button addButton;
    private final FilterTable<T> table;
    private List<T> data = new ArrayList<>();
    private AddListener addListener;

    public BaseTableView(Class<T> dataClass, BiPredicate<T, String> filterCondition, String... labelOrder){
        pane = new HBox();
        search = new TextField();
        clearButton = new Button("Clear");
        addButton = new Button("Neu");
        table = new FilterTable<>(dataClass, filterCondition)
                .setColumnOrder(labelOrder)
                .populateColumns();

        search.setPrefWidth(500);

        clearButton.setOnAction(click -> search.setText(""));
        search.setOnAction(this::filterTable);

        addButton.setOnAction(click -> {
            addListener.add();
        });

        pane.getChildren().add(search);
        pane.getChildren().add(clearButton);
        pane.getChildren().add(addButton);

        pane.setPadding(new Insets(5, 2, 5, 2));
        pane.setPrefWidth(Double.MAX_VALUE);
        pane.setSpacing(5);
        HBox.setHgrow(search, Priority.ALWAYS);

        search.setPadding(new Insets(10));
        clearButton.setPadding(new Insets(10));
        addButton.setPadding(new Insets(10));



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

    public FilterTable<T> getTable() {
        return table;
    }

    public void setAddListener(AddListener addListener) {
        this.addListener = addListener;
    }
}
