package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.database.repositories.UserRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.PaymentReceived;
import de.hhbk.jahresprojekt.model.User;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class UserModule extends AutoFetchWorkbenchModule<User> {

    private final BaseTableView<User> baseTableView;

    public UserModule() {
        super("Userverwaltung", MaterialDesignIcon.HUMAN);
        baseTableView = new BaseTableView<>(User.class,
                (data, query) -> data.getUsername().contains(query));
        baseTableView.setAddListener(() -> {
            RepositoryContainer.get(UserRepository.class).save(new User());
            refresh();
        });
        baseTableView.getTable().setOnMouseClicked(e -> {
            try {
                DetailDialog<User> detailDialog = new DetailDialog<User>(baseTableView.getTable().getSelectionModel().getSelectedItem());
                detailDialog.setOnObjectChangedListener(nValue -> {
                    RepositoryContainer.get(UserRepository.class).save(nValue);
                    refresh();
                });
                getWorkbench().showDialog(detailDialog.getDialog());
            } catch (Exception illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });

        setRepository(new UserRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {

        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        return baseTableView;
    }
}
