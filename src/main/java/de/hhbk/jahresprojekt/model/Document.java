package de.hhbk.jahresprojekt.model;

import java.util.List;

public class Document {
    private Long id;
    private String fileName;
    private List<File> versionList;

    public Document() {
    }

    public Document(Long id, String fileName, List<File> versionList) {
        this.id = id;
        this.fileName = fileName;
        this.versionList = versionList;
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
}
