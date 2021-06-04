package de.hhbk.jahresprojekt.database.repositories;

import de.hhbk.jahresprojekt.database.HibernateManager;
import de.hhbk.jahresprojekt.database.Repository;
import de.hhbk.jahresprojekt.model.File;
import de.hhbk.jahresprojekt.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FileRepository extends Repository<File> {
    public FileRepository() {
        super(File.class);
    }
}
