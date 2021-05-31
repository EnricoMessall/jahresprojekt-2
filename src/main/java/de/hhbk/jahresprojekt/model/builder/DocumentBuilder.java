package de.hhbk.jahresprojekt.model.builder;

import de.hhbk.jahresprojekt.model.Document;
import de.hhbk.jahresprojekt.model.File;
import de.hhbk.jahresprojekt.model.RentalObject;
import de.hhbk.jahresprojekt.model.Tenant;

import java.util.List;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
public final class DocumentBuilder {
    private Long id;
    private String fileName;
    private List<File> versionList;
    private List<RentalObject> relatedRentalObjects;
    private List<Tenant> relatedTenants;

    private DocumentBuilder() {
    }

    public static DocumentBuilder aDocument() {
        return new DocumentBuilder();
    }

    public DocumentBuilder withId(Long id) {
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

    public DocumentBuilder withRelatedRentalObjects(List<RentalObject> relatedRentalObjects) {
        this.relatedRentalObjects = relatedRentalObjects;
        return this;
    }

    public DocumentBuilder withRelatedTenants(List<Tenant> relatedTenants) {
        this.relatedTenants = relatedTenants;
        return this;
    }

    public Document build() {
        return new Document(id, fileName, versionList, relatedRentalObjects, relatedTenants);
    }
}
