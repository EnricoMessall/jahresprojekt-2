package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.RentalObjectRepository;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.autofetch.FetchNotifier;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class RentalObjectModule extends AutoFetchWorkbenchModule<RentalObject> {

    private final BaseTableView<RentalObject> baseTableView;

    public RentalObjectModule() {
        super("Objektverwaltung", MaterialDesignIcon.HOME);
        baseTableView = new BaseTableView<>(RentalObject.class,
                (data, query) -> data.getAdress().getStreet().contains(query),
                "Nummer", "Adresse", "Typ");
        baseTableView.setAddListener(() -> {
            RepositoryContainer.get(RentalObjectRepository.class).save(new RentalObject());
            refresh();
        });
        baseTableView.getTable().setCellFactory("Adresse", a -> a.getAdress() != null?a.getAdress().toString():"");

        baseTableView.getTable().setOnMouseClicked(e -> {
            try {
                DetailDialog<RentalObject> detailDialog = new DetailDialog<RentalObject>(baseTableView.getTable().getSelectionModel().getSelectedItem());
                detailDialog.setOnObjectChangedListener(nValue -> {
                    RepositoryContainer.get(RentalObjectRepository.class).save(nValue);
                    FetchNotifier.getInstance().requestFetch();
                });
                getWorkbench().showDialog(detailDialog.getDialog());
            } catch (Exception illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });

        setRepository(new RentalObjectRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        return baseTableView;
    }
}
