package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.TenantRepository;
import de.hhbk.jahresprojekt.model.Tenant;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class TenantModule extends AutoFetchWorkbenchModule<Tenant> {

    private final BaseTableView<Tenant> baseTableView;

    public TenantModule() {
        super("Mieterverwaltung", MaterialDesignIcon.HUMAN);
        baseTableView = new BaseTableView<>(Tenant.class,
                (data, query) -> data.getLastName().contains(query));
//        (data, query) -> true);

        setRepository(new TenantRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
