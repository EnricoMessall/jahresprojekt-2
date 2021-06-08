package de.hhbk.jahresprojekt.views.modules.view;

import de.hhbk.jahresprojekt.model.*;
import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.OnObjectChangedListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.ZoneId;
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
        for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
            if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                Label name = new Label(pd.getDisplayName());
                name.setPadding(new Insets(10, 0, 5, 0));
                getChildren().add(name);

                if(pd.getPropertyType() == int.class) addTextField(pd, Integer::parseInt);
                else if(pd.getPropertyType() == long.class) addTextField(pd, Long::parseLong);
                else if(pd.getPropertyType() == boolean.class){
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected((boolean) pd.getReadMethod().invoke(object));

                    checkBox.setOnAction(actionEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, checkBox.isSelected());
                            save();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    getChildren().add(checkBox);
                }else if(pd.getPropertyType() == String.class) addTextField(pd, s -> s);
                else if(pd.getPropertyType() == Date.class){
                    DatePicker datePicker = new DatePicker();
                    if(pd.getReadMethod().invoke(object) != null)
                    datePicker.setValue(((Date)pd.getReadMethod().invoke(object)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                    datePicker.setOnAction(keyEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, java.sql.Date.valueOf(datePicker.getValue()));
                            save();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    getChildren().add(datePicker);
                }else if (pd.getPropertyType() == List.class) {
                    addListField(pd);
                }else{
                    System.out.println(pd.getPropertyType());
                    ObjectItem objectItem = new ObjectItem(pd.getReadMethod().invoke(object));
                    objectItem.setOnObjectChangedListener(nValue -> {
                        try {
                            pd.getWriteMethod().invoke(object, nValue);
                            save();
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                    getChildren().add(objectItem);
                }
            }

        }

    }

    private <C> void addTextField(PropertyDescriptor pd, Function<String, C> function)
            throws InvocationTargetException, IllegalAccessException {
        TextField textField = new TextField();
        textField.setText(pd.getReadMethod().invoke(object)== null ? "": pd.getReadMethod().invoke(object).toString());
        textField.setOnKeyTyped(keyEvent -> {
            try {
                pd.getWriteMethod().invoke(object, function.apply(textField.getText()));
                save();
            } catch (IllegalAccessException | InvocationTargetException ignore) {}
        });
        getChildren().add(textField);
    }

    private void addListField(PropertyDescriptor pd)
            throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Type listType = ((ParameterizedType) object.getClass().getDeclaredField(pd.getName()).getGenericType()).getActualTypeArguments()[0];
        ObjectList<?> objectList = switch (listType.toString()) {
            case "class de.hhbk.jahresprojekt.model.File" -> new FileList((List<File>) pd.getReadMethod().invoke(object));
            case "class de.hhbk.jahresprojekt.model.RentalObject" -> new ObjectList<>((List<RentalObject>) pd.getReadMethod().invoke(object), RentalObject.class);
            case "class de.hhbk.jahresprojekt.model.Address" -> new ObjectList<>((List<Address>) pd.getReadMethod().invoke(object), Address.class);
            case "class de.hhbk.jahresprojekt.model.BankAccount" -> new ObjectList<>((List<BankAccount>) pd.getReadMethod().invoke(object), BankAccount.class);
            case "class de.hhbk.jahresprojekt.model.Document" -> new ObjectList<>((List<Document>) pd.getReadMethod().invoke(object), Document.class);
            case "class de.hhbk.jahresprojekt.model.Invoice" -> new ObjectList<>((List<Invoice>) pd.getReadMethod().invoke(object), Invoice.class);
            case "class de.hhbk.jahresprojekt.model.Item" -> new ObjectList<>((List<Item>) pd.getReadMethod().invoke(object), Item.class);
            case "class de.hhbk.jahresprojekt.model.PaymentReceived" -> new ObjectList<>((List<PaymentReceived>) pd.getReadMethod().invoke(object), PaymentReceived.class);
            case "class de.hhbk.jahresprojekt.model.Person" -> new ObjectList<>((List<Person>) pd.getReadMethod().invoke(object), Person.class);
            case "class de.hhbk.jahresprojekt.model.RentalType" -> new ObjectList<>((List<RentalType>) pd.getReadMethod().invoke(object), RentalType.class);
            case "class de.hhbk.jahresprojekt.model.Role" -> new ObjectList<>((List<Role>) pd.getReadMethod().invoke(object), Role.class);
            case "class de.hhbk.jahresprojekt.model.RoleType" -> new ObjectList<>((List<RoleType>) pd.getReadMethod().invoke(object), RoleType.class);
            case "class de.hhbk.jahresprojekt.model.Tenant" -> new ObjectList<>((List<Tenant>) pd.getReadMethod().invoke(object), Tenant.class);
            case "class de.hhbk.jahresprojekt.model.User" -> new ObjectList<>((List<User>) pd.getReadMethod().invoke(object), User.class);
            default -> new ObjectList<>((List<Object>) pd.getReadMethod().invoke(object), Object.class);
        };
        objectList.setOnChangeListener(nValue -> save());
        getChildren().add(objectList);
    }

    public void save(){
        onObjectChangedListener.changed(object);
    }
}
