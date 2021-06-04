package de.hhbk.jahresprojekt.views.modules.view;

import com.sun.javafx.scene.control.IntegerField;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.model.*;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.OnObjectChangedListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DetailForm<T> extends VBox {
    private OnObjectChangedListener<T> onObjectChangedListener;
    private T object;

    public DetailForm(T object, OnObjectChangedListener<T> onObjectChangedListener) throws Exception {
        this.object = object;
        this.onObjectChangedListener = onObjectChangedListener;
        setPadding(new Insets(10));
        for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
            if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                System.out.println(pd.getName() + ": " + pd.getReadMethod().invoke(object) + ", " + pd.getPropertyType());
                Label name = new Label(pd.getDisplayName());
                name.setPadding(new Insets(10, 0, 5, 0));
                getChildren().add(name);

                if(pd.getPropertyType().toString().equals("int")){
                    TextField textField = new TextField();
                    textField.setText(String.valueOf(pd.getReadMethod().invoke(object)));
                    textField.setOnKeyTyped(keyEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, Integer.parseInt(textField.getText()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                    getChildren().add(textField);
                }else

                if(pd.getPropertyType().toString().equals("class java.lang.Long")){
                    TextField textField = new TextField();
                    textField.setText(String.valueOf(pd.getReadMethod().invoke(object)));

                    textField.setOnKeyTyped(keyEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, (long)Integer.parseInt(textField.getText()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    getChildren().add(textField);
                }else

                if(pd.getPropertyType().toString().equals("boolean")){
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected((boolean) pd.getReadMethod().invoke(object));

                    checkBox.setOnAction(actionEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, checkBox.isSelected());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    getChildren().add(checkBox);
                }else

                if(pd.getPropertyType().toString().equals("class java.lang.String")){
                    TextField textField = new TextField();
                    textField.setText(pd.getReadMethod().invoke(object)== null?"":pd.getReadMethod().invoke(object).toString());

                    textField.setOnKeyTyped(keyEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, textField.getText());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    getChildren().add(textField);
                }else

                if(pd.getPropertyType().toString().equals("class java.util.Date")){
                    DatePicker datePicker = new DatePicker();
                    if(pd.getReadMethod().invoke(object) != null)
                    datePicker.setValue(((Date)pd.getReadMethod().invoke(object)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                    datePicker.setOnAction(keyEvent -> {
                        try {
                            pd.getWriteMethod().invoke(object, (Date)java.sql.Date.valueOf(datePicker.getValue()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });

                    getChildren().add(datePicker);
                }else


                //Listen
                if (pd.getPropertyType().toString().contains("java.util.List")) {
                    Type listType = ((ParameterizedType) object.getClass().getDeclaredField(pd.getName()).getGenericType()).getActualTypeArguments()[0];
                    ObjectList<?> objectList = switch (listType.toString()) {
                        case "class de.hhbk.jahresprojekt.model.File" -> new ObjectList<File>((List<File>) pd.getReadMethod().invoke(object), File.class);
                        case "class de.hhbk.jahresprojekt.model.RentalObject" -> new ObjectList<RentalObject>((List<RentalObject>) pd.getReadMethod().invoke(object), RentalObject.class);
                        case "class de.hhbk.jahresprojekt.model.Address" -> new ObjectList<Address>((List<Address>) pd.getReadMethod().invoke(object), Address.class);
                        case "class de.hhbk.jahresprojekt.model.BankAccount" -> new ObjectList<BankAccount>((List<BankAccount>) pd.getReadMethod().invoke(object), BankAccount.class);
                        case "class de.hhbk.jahresprojekt.model.Document" -> new ObjectList<Document>((List<Document>) pd.getReadMethod().invoke(object), Document.class);
                        case "class de.hhbk.jahresprojekt.model.Invoice" -> new ObjectList<Invoice>((List<Invoice>) pd.getReadMethod().invoke(object), Invoice.class);
                        case "class de.hhbk.jahresprojekt.model.Item" -> new ObjectList<Item>((List<Item>) pd.getReadMethod().invoke(object), Item.class);
                        case "class de.hhbk.jahresprojekt.model.PaymentReceived" -> new ObjectList<PaymentReceived>((List<PaymentReceived>) pd.getReadMethod().invoke(object), PaymentReceived.class);
                        case "class de.hhbk.jahresprojekt.model.Person" -> new ObjectList<Person>((List<Person>) pd.getReadMethod().invoke(object), Person.class);
                        case "class de.hhbk.jahresprojekt.model.RentalType" -> new ObjectList<RentalType>((List<RentalType>) pd.getReadMethod().invoke(object), RentalType.class);
                        case "class de.hhbk.jahresprojekt.model.Role" -> new ObjectList<Role>((List<Role>) pd.getReadMethod().invoke(object), Role.class);
                        case "class de.hhbk.jahresprojekt.model.RoleType" -> new ObjectList<RoleType>((List<RoleType>) pd.getReadMethod().invoke(object), RoleType.class);
                        case "class de.hhbk.jahresprojekt.model.Tenant" -> new ObjectList<Tenant>((List<Tenant>) pd.getReadMethod().invoke(object), Tenant.class);
                        case "class de.hhbk.jahresprojekt.model.User" -> new ObjectList<User>((List<User>) pd.getReadMethod().invoke(object), User.class);
                        default -> new ObjectList<Object>((List<Object>) pd.getReadMethod().invoke(object), Object.class);
                    };
                    getChildren().add(objectList);
                }else

                {
                    ObjectItem<?> objectItem = switch (pd.getPropertyType().toString()) {
                        case "class de.hhbk.jahresprojekt.model.File" -> new ObjectItem<File>(File.class, (File) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.RentalObject" -> new ObjectItem<RentalObject>(RentalObject.class, (RentalObject) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.Tenant" -> new ObjectItem<Tenant>(Tenant.class, (Tenant) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.Address" -> new ObjectItem<Address>(Address.class, (Address) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.BankAccount" -> new ObjectItem<BankAccount>(BankAccount.class, (BankAccount) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.Document" -> new ObjectItem<Document>(Document.class, (Document) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.Invoice" -> new ObjectItem<Invoice>(Invoice.class, (Invoice) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.Item" -> new ObjectItem<Item>(Item.class, (Item) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.PaymentReceived" -> new ObjectItem<PaymentReceived>(PaymentReceived.class, (PaymentReceived) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.Person" -> new ObjectItem<Person>(Person.class, (Person) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.RentalType" -> new ObjectItem<RentalType>(RentalType.class, (RentalType) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.Role" -> new ObjectItem<Role>(Role.class, (Role) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.RoleType" -> new ObjectItem<RoleType>(RoleType.class, (RoleType) pd.getReadMethod().invoke(object));
                        case "class de.hhbk.jahresprojekt.model.User" -> new ObjectItem<User>(User.class, (User) pd.getReadMethod().invoke(object));
                        default -> new ObjectItem<Object>(Object.class, (Object) pd.getReadMethod().invoke(object));
                    };
                    objectItem.setOnObjectChangedListener(nValue -> {
                        try {
                            pd.getWriteMethod().invoke(object, nValue);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                    getChildren().add(objectItem);
                }
            }

        }

    }

    public void save(){
        onObjectChangedListener.changed(object);
    }
}
