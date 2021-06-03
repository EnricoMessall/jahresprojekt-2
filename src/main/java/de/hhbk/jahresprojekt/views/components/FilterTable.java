package de.hhbk.jahresprojekt.views.components;


import de.hhbk.jahresprojekt.views.annotations.TableField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilterTable<T> extends TableView<T> {

    private final Class<T> tClass;
    private List<String> labelOrder;

    public FilterTable(Class<T> theClass){
        this.tClass = theClass;
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
        getColumns().add(column);
    }
}
