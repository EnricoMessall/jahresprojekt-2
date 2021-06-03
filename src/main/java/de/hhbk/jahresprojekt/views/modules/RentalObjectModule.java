package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.components.FilterTable;
import de.hhbk.jahresprojekt.views.modules.view.BaseView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RentalObjectModule extends WorkbenchModule {

    private final BaseView baseView;

    public RentalObjectModule() {
        super("Objektverwaltung", MaterialDesignIcon.HOME);
        baseView = new BaseView();
        initTable();
    }

    private void initTable(){
        GridPane pane = new GridPane();
        TextField field = new TextField();
        field.setMinWidth(500);
        pane.add(field, 0, 0);
        pane.add(new Button("Clear"), 1, 0);

        FilterTable<RentalObject> table = new FilterTable<>(RentalObject.class)
                .setColumnOrder("Nummer", "Adresse", "Typ", "Kommerziell")
                .populateColumns();
        baseView.setTop(pane);
        baseView.setCenter(table);
    }

    @Override
    public Node activate() {
        return baseView;
    }
}
