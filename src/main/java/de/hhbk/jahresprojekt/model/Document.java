package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @TableField
    private Long id;
    @TableField
    private String fileName;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> versionList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "document_rental_objects",
            joinColumns = @JoinColumn(name = "id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RentalObject> relatedRentalObjects;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "document_tenants",
            joinColumns = @JoinColumn(name = "id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
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

    @Override
    public String toString() {
        return String.join(", ", String.valueOf(id), fileName, "Verion: " + versionList.size());
    }
}
