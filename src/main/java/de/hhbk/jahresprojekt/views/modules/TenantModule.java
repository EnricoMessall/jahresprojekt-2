package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.TenantRepository;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.Tenant;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.autofetch.FetchNotifier;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class TenantModule extends AutoFetchWorkbenchModule<Tenant> {

    private final BaseTableView<Tenant> baseTableView;

    public TenantModule() {
        super("Mieterverwaltung", MaterialDesignIcon.HUMAN);
        baseTableView = new BaseTableView<>(Tenant.class, (data, query) -> data.getLastName().contains(query));
        baseTableView.setAddListener(() -> {
            RepositoryContainer.get(TenantRepository.class).save(new Tenant());
            refresh();
        });
        baseTableView.getTable().setOnMouseClicked(e -> {
            try {
                DetailDialog<Tenant> detailDialog = new DetailDialog<Tenant>(baseTableView.getTable().getSelectionModel().getSelectedItem());
                detailDialog.setOnObjectChangedListener(nValue -> {
                    RepositoryContainer.get(TenantRepository.class).save(nValue);
                    FetchNotifier.getInstance().requestFetch();
                });
                getWorkbench().showDialog(detailDialog.getDialog());
            } catch (Exception illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });

        setRepository(new TenantRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {

        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        return baseTableView;
    }
}
