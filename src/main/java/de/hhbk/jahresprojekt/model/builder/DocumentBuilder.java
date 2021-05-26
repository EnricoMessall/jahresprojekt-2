package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.File;

import java.util.List;

public final class DocumentBuilder {
    private int id;
    private String fileName;
    private List<File> versionList;

    private DocumentBuilder() {
    }

    public static DocumentBuilder aDocument() {
        return new DocumentBuilder();
    }

    public DocumentBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public DocumentBuilder withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public DocumentBuilder withVersionList(List<File> versionList) {
        this.versionList = versionList;
        return this;
    }

    public Document build() {
        return new Document(id, fileName, versionList);
    }
}
