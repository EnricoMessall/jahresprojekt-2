package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class TenantModule extends WorkbenchModule {

    public TenantModule() {
        super("Mieterverwaltung", MaterialDesignIcon.HUMAN);
    }

    @Override
    public Node activate() {
        return new Label("Mieter");
    }
}
