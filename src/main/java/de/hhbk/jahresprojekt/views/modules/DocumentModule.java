package de.hhbk.jahresprojekt.views.modules;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.hhbk.jahresprojekt.database.RepositoryContainer;
import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.help.WorkbenchHolder;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

/**
 * @author Frederick Hafemann
 * @author Enrico Messall
 */
public class DocumentModule extends WorkbenchModule {

    protected final BaseTableView<Document> baseTableView;

    public DocumentModule() {
        super("Dokumente", MaterialDesignIcon.FILE_DOCUMENT);

        baseTableView = new BaseTableView<>(Document.class,
                RepositoryContainer.get(DocumentRepository.class),
                (data, query) -> data.getFileName().contains(query));
    }

    @Override
    public Node activate() {
        WorkbenchHolder.getInstance().setWorkbench(getWorkbench());
        baseTableView.refreshData();
        return baseTableView;
    }
}
