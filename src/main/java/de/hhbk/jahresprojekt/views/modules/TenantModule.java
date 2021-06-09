package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.TenantRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Tenant;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class TenantModule extends WorkbenchModule {

    private final BaseTableView<Tenant> baseTableView;

    public TenantModule() {
        super("Mieterverwaltung", MaterialDesignIcon.HUMAN);
        baseTableView = new BaseTableView<>(Tenant.class,
                RepositoryContainer.get(TenantRepository.class),
                (data, query) -> data.getLastName().contains(query));
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        baseTableView.getTable().refresh();
        return baseTableView;
    }
}
