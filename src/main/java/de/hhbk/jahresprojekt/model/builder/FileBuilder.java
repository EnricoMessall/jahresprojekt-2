package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.File;

import java.util.Date;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
public final class FileBuilder {
    private Long id;
    private String path;
    private Date date;

    private FileBuilder() {
    }

    public static FileBuilder aFile() {
        return new FileBuilder();
    }

    public FileBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FileBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    public FileBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public File build() {
        return new File(id, path, date);
    }
}
