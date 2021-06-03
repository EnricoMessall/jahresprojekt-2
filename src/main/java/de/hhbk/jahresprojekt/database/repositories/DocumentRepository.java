package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.User;

public class DocumentRepository extends Repository<Document> {

    public DocumentRepository() {
        super(Document.class);
    }
}
