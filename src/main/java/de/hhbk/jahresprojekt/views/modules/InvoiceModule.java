package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.InvoiceRepository;
import de.hhbk.jahresprojekt.database.RentalObjectRepository;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.Invoice;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class InvoiceModule extends AutoFetchWorkbenchModule<Invoice> {

    private BaseTableView<Invoice> baseTableView;

    public InvoiceModule() {
        super("Rechnungen", MaterialDesignIcon.BOOK);
        baseTableView = new BaseTableView<>(Invoice.class, (data, query) -> data.isSettled());
        baseTableView.setAddListener(() -> {
            RepositoryContainer.get(InvoiceRepository.class).save(new Invoice());
            refresh();
        });
        baseTableView.getTable().setOnMouseClicked(e -> {
            try {
                DetailDialog<Invoice> detailDialog = new DetailDialog<Invoice>(baseTableView.getTable().getSelectionModel().getSelectedItem());
                detailDialog.setOnObjectChangedListener(nValue -> {
                    RepositoryContainer.get(InvoiceRepository.class).save(nValue);
                    refresh();
                });
                getWorkbench().showDialog(detailDialog.getDialog());
            } catch (Exception illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });

        setRepository(new InvoiceRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {

        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        return baseTableView;
    }
}
