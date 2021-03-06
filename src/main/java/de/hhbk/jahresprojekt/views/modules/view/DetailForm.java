package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.model.File;
import de.hhbk.jahresprojekt.model.Tenant;
import de.hhbk.jahresprojekt.views.annotations.TableField;
import de.hhbk.jahresprojekt.views.components.Error;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class DetailForm<T> extends VBox {
    private final OnObjectChangedListener<T> onObjectChangedListener;
    private final T object;

    public DetailForm(T object, OnObjectChangedListener<T> onObjectChangedListener) throws Exception {
        this.object = object;
        this.onObjectChangedListener = onObjectChangedListener;
        setPadding(new Insets(10));
        List<PropertyDescriptor> descriptors = Arrays.asList(Introspector.getBeanInfo(object.getClass())
                .getPropertyDescriptors());
        descriptors.sort((a, b) -> sort(a.getPropertyType(), b.getPropertyType()));
        for (PropertyDescriptor pd : descriptors) {
            if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                Field field;
                try{
                    field = object.getClass().getDeclaredField(pd.getName());
                }catch (NoSuchFieldException nsfe){
                    field = object.getClass().getSuperclass().getDeclaredField(pd.getName());
                }
                String value = field.isAnnotationPresent(TableField.class) ?
                        field.getAnnotation(TableField.class).label() :
                        pd.getDisplayName();
                if(value.equals("")) value = pd.getDisplayName();
                Label name = new Label(value);
                name.setPadding(new Insets(10, 0, 5, 0));
                getChildren().add(name);

                if(pd.getPropertyType() == int.class || pd.getPropertyType() == Integer.class)
                    addTextField(pd, Integer::parseInt, true);
                else if(pd.getPropertyType().getSuperclass() == Enum.class){
                    EnumItem objectItem = new EnumItem((Enum) pd.getReadMethod().invoke(object), pd.getPropertyType());
                    objectItem.setOnObjectChangedListener(nValue -> {
                        try {
                            pd.getWriteMethod().invoke(object, nValue);
                            save();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            new Error(e.getMessage());
                        }
                    });
                    getChildren().add(objectItem);
                }
                else if(pd.getPropertyType() == long.class || pd.getPropertyType() == Long.class)
                    addTextField(pd, Long::parseLong, true);
                else if(pd.getPropertyType() == boolean.class || pd.getPropertyType() == Boolean.class){
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected((boolean) pd.getReadMethod().invoke(object));

                    checkBox.setOnAction(actionEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, checkBox.isSelected());
                            save();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            new Error(e.getMessage());

                        }
                    });

                    getChildren().add(checkBox);
                }else if(pd.getPropertyType() == String.class) addTextField(pd, s -> s, false);
                else if(pd.getPropertyType() == Date.class){
                    DatePicker datePicker = new DatePicker();
                    if(pd.getReadMethod().invoke(object) != null){
                        Timestamp timestamp = (Timestamp) pd.getReadMethod().invoke(object);
                        LocalDate date = timestamp.toLocalDateTime().atZone(ZoneId.systemDefault()).toLocalDate();
                        datePicker.setValue(date);
                    }

                    datePicker.setOnAction(keyEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, java.sql.Date.valueOf(datePicker.getValue()));
                            save();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            new Error(e.getMessage());

                        }
                    });

                    getChildren().add(datePicker);
                }else if (pd.getPropertyType() == List.class) {
                    addListField(pd);
                }else{
                    ObjectItem objectItem = new ObjectItem(pd.getReadMethod().invoke(object), pd.getPropertyType());
                    objectItem.setOnObjectChangedListener(nValue -> {
                        try {
                            pd.getWriteMethod().invoke(object, nValue);
                            save();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            new Error(e.getMessage());
                        }
                    });
                    getChildren().add(objectItem);
                }
            }

        }

    }

    private <C> void addTextField(PropertyDescriptor pd, Function<String, C> function, boolean numberOnly)
            throws InvocationTargetException, IllegalAccessException {
        TextField textField = new TextField();
        if(numberOnly) textField.setTextFormatter(enableNumberOnly());
        textField.setText(pd.getReadMethod().invoke(object)== null ? "": pd.getReadMethod().invoke(object).toString());
        if(pd.getWriteMethod() == null) textField.setDisable(true);
        textField.setOnKeyTyped(keyEvent -> {
            try {
                pd.getWriteMethod().invoke(object, function.apply(textField.getText()));
                save();
            } catch (Exception ignore) {}
        });
        getChildren().add(textField);
    }

    protected TextFormatter<String> enableNumberOnly(){
        return new TextFormatter<>(change -> change.getText().matches("[0-9]*") ? change : null);
    }

    private int sort(Class<?> a, Class<?> b){
        return Integer.compare(getValue(a), getValue(b));
    }

    private int getValue(Class<?> tClass){
        if(tClass == List.class) return 99;
        if(tClass == Date.class) return 4;
        if(tClass == Boolean.class || tClass == boolean.class) return 3;
        if(tClass == String.class) return 2;
        if(tClass == Integer.class || tClass == int.class) return 1;
        if(tClass == Long.class || tClass == long.class) return 0;
        return 20;
    }

    private void addListField(PropertyDescriptor pd)
            throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Class<?> tClass = (Class<?>)((ParameterizedType) object.getClass().getDeclaredField(pd.getName()).getGenericType())
                .getActualTypeArguments()[0];
        Object data = pd.getReadMethod().invoke(object);
        if(data == null) data = new ArrayList<>();



        ObjectList<?> objectList = tClass == File.class ? new FileList((List<File>) pd.getReadMethod().invoke(object)) :
                new ObjectList<>(data, tClass);
        objectList.setOnChangeListener(nValue -> save());
        objectList.setOnAdd(value -> {
            try {
                object.getClass().getMethod("add" + pd.getName(), tClass).invoke(object, value);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            }
        });
        objectList.setOnRemove(value -> {
            try {
                object.getClass().getMethod("remove" + pd.getName(), tClass).invoke(object, value);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            }
        });
        getChildren().add(objectList);
    }

    public void save(){
        onObjectChangedListener.changed(object);
    }
}
