package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.components.FilterTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class BaseTableView<T> extends BorderPane {

    private final HBox pane;
    private final TextField search;
    private final Button clearButton;
    private final Button addButton;
    private final FilterTable<T> table;
    private List<T> data = new ArrayList<>();
    private final Class<T> dataClass;
    private final Repository<T> repository;

    public BaseTableView(Class<T> dataClass, Repository<T> repository,
                         BiPredicate<T, String> filterCondition, String... labelOrder){
        this.pane = new HBox();
        this.search = new TextField();
        this.clearButton = new Button("Clear");
        this.addButton = new Button("New");
        this.table = new FilterTable<>(dataClass, filterCondition)
                .setColumnOrder(labelOrder)
                .populateColumns();
        this.dataClass = dataClass;
        this.repository = repository;
        search.setPrefWidth(500);

        clearButton.setOnAction(click -> search.setText(""));
        search.setOnAction(this::filterTable);

        addButton.setOnAction(click -> createNew());

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
        refreshData();
        table.refresh();
        addMouseClick();
    }

    public void refreshData(){
        setData(this.repository.findAll());
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

    private void createNew(){
        try {
            T object = repository.save(dataClass.getDeclaredConstructor().newInstance());
            refreshData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMouseClick(){
        getTable().setOnMouseClicked(e -> {
            try {
                T model = getTable().getSelectionModel().getSelectedItem();
                if(model == null) return;
                DetailDialog<T> detailDialog = new DetailDialog<>(model);
                detailDialog.setOnObjectChangedListener(nValue -> {
                    repository.save(nValue);
                    table.refresh();
                });
                getTable().getSelectionModel().clearSelection();
                WorkbenchHolder.getInstance().getWorkbench().showDialog(detailDialog.getDialog());
            } catch (Exception illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });
    }

    public FilterTable<T> getTable() {
        return table;
    }
}
