package de.hhbk.jahresprojekt.views.components;


import de.hhbk.jahresprojekt.views.annotations.TableField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class FilterTable<T> extends TableView<T> {

    private final Class<T> tClass;
    // Predicate to filter data by search query
    private final BiPredicate<T, String> filterCondition;
    // Order for table columns
    private List<String> labelOrder;

    public FilterTable(Class<T> theClass, BiPredicate<T, String> filterCondition){
        this.tClass = theClass;
        this.filterCondition = filterCondition;
    }

    public BiPredicate<T, String> getFilterCondition() {
        return filterCondition;
    }

    public FilterTable<T> setColumnOrder(String... labelOrder){
        this.labelOrder = Arrays.asList(labelOrder);
        return this;
    }

    public FilterTable<T> populateColumns() {
        Arrays.stream(tClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(TableField.class))
                .forEach(this::addColumnFromField);
        getColumns().sort(Comparator.comparing(this::index));
        return this;
    }

    private int index(TableColumn<T, ?> column){
        int index = labelOrder.indexOf(column.getText());
        return index == -1 ? 999 : index;
    }

    private void addColumnFromField(Field field){
        String value = field.getAnnotation(TableField.class).label();
        if(value.equals("")) value = field.getName();
        TableColumn<T, ?> column = new TableColumn<>(value);
        column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
        column.setReorderable(false);
        getColumns().add(column);
    }
}
