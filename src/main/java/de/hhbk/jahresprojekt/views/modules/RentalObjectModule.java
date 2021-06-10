package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.RentalObjectRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class RentalObjectModule extends WorkbenchModule {

    private final BaseTableView<RentalObject> baseTableView;

    public RentalObjectModule() {
        super("Objektverwaltung", MaterialDesignIcon.HOME);
        baseTableView = new BaseTableView<>(RentalObject.class,
                (data, query) -> {
                    if(data.getAddress() != null)
                        return data.getAddress().getStreet().contains(query);
                    return false;
                },
                "Nummer", "Adresse", "Typ");
        baseTableView.getTable().setCellFactory("Adresse", a -> a.getAddress() != null?a.getAddress().toString():"");
        baseTableView.getTable().getItems().forEach(RentalObject::updateCosts);
        baseTableView.getTable().refresh();
    }

    @Override
    public void deactivate() {
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        baseTableView.refreshData();
        baseTableView.getTable().getItems().forEach(RentalObject::updateCosts);
        baseTableView.getTable().refresh();
        return baseTableView;
    }
}
