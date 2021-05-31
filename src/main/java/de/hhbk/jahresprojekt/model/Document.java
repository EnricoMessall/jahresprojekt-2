package de.hhbk.jahresprojekt.model;

import java.util.List;

public class Document {
    private Long id;
    private String fileName;
    private List<File> versionList;
    private List<RentalObject> relatedRentalObjects;
    private List<Tenant> relatedTenants;

    public Document() {
    }

    public Document(Long id, String fileName, List<File> versionList, List<RentalObject> relatedRentalObjects, List<Tenant> relatedTenants) {
        this.id = id;
        this.fileName = fileName;
        this.versionList = versionList;
        this.relatedRentalObjects = relatedRentalObjects;
        this.relatedTenants = relatedTenants;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<File> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<File> versionList) {
        this.versionList = versionList;
    }

    public List<RentalObject> getRelatedRentalObjects() {
        return relatedRentalObjects;
    }

    public void setRelatedRentalObjects(List<RentalObject> relatedRentalObjects) {
        this.relatedRentalObjects = relatedRentalObjects;
    }

    public List<Tenant> getRelatedTenants() {
        return relatedTenants;
    }

    public void setRelatedTenants(List<Tenant> relatedTenants) {
        this.relatedTenants = relatedTenants;
    }
}
