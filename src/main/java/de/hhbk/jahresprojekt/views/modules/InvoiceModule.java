package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class InvoiceModule extends WorkbenchModule {

    private BaseTableView<Invoice> baseTableView;

    public InvoiceModule() {
        super("Rechnungen", MaterialDesignIcon.BOOK);
        baseTableView = new BaseTableView<>(Invoice.class, "Nummer", "Adresse", "Typ");
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
