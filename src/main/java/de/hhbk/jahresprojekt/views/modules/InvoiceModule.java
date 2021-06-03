package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class InvoiceModule extends WorkbenchModule {

    public InvoiceModule() {
        super("Rechnungen", MaterialDesignIcon.BOOK);
    }

    @Override
    public Node activate() {
        return new Label("Rechnungen");
    }
}
