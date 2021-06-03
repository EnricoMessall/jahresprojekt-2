package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.repositories.PaymentReceivedRepository;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class PaymentReceivedModule extends AutoFetchWorkbenchModule<PaymentReceived> {

    private final BaseTableView<PaymentReceived> baseTableView;

    public PaymentReceivedModule() {
        super("Zahlungseingänge", MaterialDesignIcon.PANDA);
        baseTableView = new BaseTableView<>(PaymentReceived.class,
                (data, query) -> data.getTenant().getLastName().contains(query));

        setRepository(new PaymentReceivedRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
