package de.hhbk.jahresprojekt.views.modules;

import de.hhbk.jahresprojekt.database.repositories.DocumentRepository;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.User;
import de.hhbk.jahresprojekt.views.modules.autofetch.AutoFetchWorkbenchModule;
import de.hhbk.jahresprojekt.views.modules.view.BaseTableView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;

public class DocumentModule extends AutoFetchWorkbenchModule<Document> {

    private final BaseTableView<Document> baseTableView;

    public DocumentModule() {
        super("Dokumente", MaterialDesignIcon.BOOK);
        baseTableView = new BaseTableView<>(Document.class,
                (data, query) -> data.getFileName().contains(query));

        setRepository(new DocumentRepository());
        setOnFetchedListener(baseTableView::setData);
        refresh();
    }

    @Override
    public Node activate() {
        return baseTableView;
    }
}
