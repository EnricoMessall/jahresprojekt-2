package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.PaymentReceivedRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.FetchNotifier;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 */
public class PaymentReceivedModule extends WorkbenchModule {

    private final BaseTableView<PaymentReceived> baseTableView;

    public PaymentReceivedModule() {
        super("Zahlungseingänge", MaterialDesignIcon.PANDA);
        baseTableView = new BaseTableView<>(PaymentReceived.class,
                RepositoryContainer.get(PaymentReceivedRepository.class),
                (data, query) -> data.getTenant().getLastName().contains(query));
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        baseTableView.getTable().refresh();
        return baseTableView;
    }
}
