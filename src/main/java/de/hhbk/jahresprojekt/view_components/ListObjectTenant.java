package de.hhbk.jahresprojekt.view_components;

import de.hhbk.jahresprojekt.model.Tenant;
import javafx.scene.control.Label;

public class ListObjectTenant extends ListObject<Tenant>{
    public ListObjectTenant(Tenant tenant){
        super(tenant);
        getChildren().add(new Label(tenant.getTitle()));
        getChildren().add(new Label(tenant.getLastName() + ", " + tenant.getFirstName()));
        getChildren().add(new Label(tenant.getTitle()));
    }
}
