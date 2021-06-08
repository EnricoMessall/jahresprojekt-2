package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.views.components.DetailDialog;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.autofetch.FetchNotifier;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 */
public class DocumentModule extends AutoFetchWorkbenchModule<Document> {

    protected final BaseTableView<Document> baseTableView;

    public DocumentModule() {
        super("Dokumente", MaterialDesignIcon.FILE_DOCUMENT);

        baseTableView = new BaseTableView<>(Document.class,
                (data, query) -> data.getFileName().contains(query));

        baseTableView.setAddListener(() -> {
            RepositoryContainer.get(DocumentRepository.class).save(new Document());
            refresh();
        });

        baseTableView.getTable().setOnMouseClicked(e -> {
            try {
                DetailDialog<Document> detailDialog = new DetailDialog<Document>(baseTableView.getTable().getSelectionModel().getSelectedItem());
                detailDialog.setOnObjectChangedListener(nValue -> {
                    RepositoryContainer.get(DocumentRepository.class).save(nValue);
                    FetchNotifier.getInstance().requestFetch();
                });
                getWorkbench().showDialog(detailDialog.getDialog());
            } catch (Exception illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });

        setRepository(new DocumentRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        return baseTableView;
    }
}
