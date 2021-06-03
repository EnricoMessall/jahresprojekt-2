package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class RentalObjectModule extends WorkbenchModule {

    private final BaseTableView<RentalObject> baseTableView;

    public RentalObjectModule() {
        super("Objektverwaltung", MaterialDesignIcon.HOME);
        baseTableView = new BaseTableView<>(RentalObject.class, "Nummer", "Adresse", "Typ");
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
