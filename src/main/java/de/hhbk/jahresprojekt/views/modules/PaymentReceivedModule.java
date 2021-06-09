package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.PaymentReceivedRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.PaymentReceived;
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
        baseTableView.getTable().setOnMouseClicked(e -> {
            try {
                DetailDialog<PaymentReceived> detailDialog = new DetailDialog<PaymentReceived>(baseTableView.getTable().getSelectionModel().getSelectedItem());
                detailDialog.setOnObjectChangedListener(nValue -> {
                    RepositoryContainer.get(PaymentReceivedRepository.class).save(nValue);
                    FetchNotifier.getInstance().requestFetch();
                });
                getWorkbench().showDialog(detailDialog.getDialog());
            } catch (Exception illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });
    }

    @Override
    public Node activate() {

        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        return baseTableView;
    }
}
