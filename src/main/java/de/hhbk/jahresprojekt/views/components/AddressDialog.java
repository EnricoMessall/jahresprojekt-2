package de.hhbk.jahresprojekt.views.components;

import de.hhbk.jahresprojekt.database.AddressRepository;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.model.Address;
import de.hhbk.jahresprojekt.model.Item;
import de.hhbk.jahresprojekt.model.builder.AdressBuilder;
import de.hhbk.jahresprojekt.model.builder.ItemBuilder;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddressDialog<T> extends Dialog<Address>{
    VBox vBox;
    TextField street, zipcode, city, country;

    public AddressDialog() {
        super(Address.class);
    }

    @Override
    Node initView() {
        vBox = new VBox();

        street = new TextField();
        zipcode = new TextField();
        city = new TextField();
        country = new TextField();

        vBox.getChildren().add(new Label("Strasse"));
        vBox.getChildren().add(street);
        vBox.getChildren().add(new Label("PLZ"));
        vBox.getChildren().add(zipcode);
        vBox.getChildren().add(new Label("Stadt"));
        vBox.getChildren().add(city);
        vBox.getChildren().add(new Label("Land"));
        vBox.getChildren().add(country);
        return vBox;
    }

    @Override
    Address getChangedObject() {
        Address address = AdressBuilder.anAdress().withStreet(street.getText()).withZipCode(zipcode.getText()).withCity(city.getText()).withCountry(country.getText()).build();
        RepositoryContainer.get(AddressRepository.class).save(address);
        return address;
    }
}
