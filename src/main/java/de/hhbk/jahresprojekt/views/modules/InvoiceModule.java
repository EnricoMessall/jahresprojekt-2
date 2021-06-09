package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.InvoiceRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.FetchNotifier;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 */
public class InvoiceModule extends WorkbenchModule {

    private BaseTableView<Invoice> baseTableView;

    public InvoiceModule() {
        super("Rechnungen", MaterialDesignIcon.BOOK);
        baseTableView = new BaseTableView<>(Invoice.class,
                RepositoryContainer.get(InvoiceRepository.class),
                (data, query) -> data.isSettled());
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        baseTableView.getTable().refresh();
        return baseTableView;
    }
}
