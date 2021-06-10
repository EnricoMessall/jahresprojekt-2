package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.PaymentReceivedRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.components.Error;
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
    private final Button deleteButton;
    private final FilterTable<T> table;
    private List<T> data = new ArrayList<>();
    private final Class<T> dataClass;
    private final Repository<T> repository;

    public BaseTableView(Class<T> dataClass, BiPredicate<T, String> filterCondition, String... labelOrder){
        this.pane = new HBox();
        this.search = new TextField();
        this.clearButton = new Button("Clear");
        this.addButton = new Button("New");
        this.deleteButton = new Button("Delete");
        this.table = new FilterTable<>(dataClass, filterCondition)
                .setColumnOrder(labelOrder)
                .populateColumns();
        this.dataClass = dataClass;
        this.repository = RepositoryContainer.get(dataClass);
        search.setPrefWidth(500);

        clearButton.setOnAction(click -> search.setText(""));
        search.setOnAction(this::filterTable);

        addButton.setOnAction(click -> createNew());
        deleteButton.setOnAction(click -> deleteSelectedObject());

        pane.getChildren().add(search);
        pane.getChildren().add(clearButton);
        pane.getChildren().add(deleteButton);
        pane.getChildren().add(addButton);

        pane.setPadding(new Insets(5, 2, 5, 2));
        pane.setPrefWidth(Double.MAX_VALUE);
        pane.setSpacing(5);
        HBox.setHgrow(search, Priority.ALWAYS);

        search.setPadding(new Insets(10));
        clearButton.setPadding(new Insets(10));
        addButton.setPadding(new Insets(10));
        deleteButton.setPadding(new Insets(10));

        setTop(pane);
        setCenter(table);
        refreshData();
        addMouseClick();
    }

    public void refreshData(){
        setData(this.repository.findAll());
    }

    public void setData(List<T> data){
        this.data = data;
        filterTable(null);
    }

    private void deleteSelectedObject(){

        try {
            T model = getTable().getSelectionModel().getSelectedItem();
            repository.delete(model);
            getTable().getItems().remove(model);
        } catch (Exception illegalAccessException) {
            new Error(illegalAccessException.getMessage());

        }
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
            getTable().getItems().add(object);
            openDialog(object);
        } catch (Exception e) {
            new Error(e.getMessage());
        }
    }

    private void addMouseClick(){
        getTable().setOnMouseClicked(e -> {
            try {
                if(e.getClickCount() != 2) return;
                T model = getTable().getSelectionModel().getSelectedItem();
                if(model == null) return;
                openDialog(model);
            } catch (Exception illegalAccessException) {
                new Error(illegalAccessException.getMessage());

            }
        });
    }

    private void openDialog(T object) throws Exception {
        DetailDialog<T> detailDialog = new DetailDialog<>(object);
        detailDialog.setOnObjectChangedListener(nValue -> {
            repository.save(nValue);
            table.refresh();
        });
        getTable().getSelectionModel().clearSelection();
        WorkbenchHolder.getInstance().getWorkbench().showDialog(detailDialog.getDialog());
    }

    public FilterTable<T> getTable() {
        return table;
    }
}
