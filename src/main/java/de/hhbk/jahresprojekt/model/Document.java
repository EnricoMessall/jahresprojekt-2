package de.hhbk.jahresprojekt.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String fileName;
    @OneToMany
    private List<File> versionList;
    @ManyToMany
    private List<RentalObject> relatedRentalObjects;
    @ManyToMany
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
