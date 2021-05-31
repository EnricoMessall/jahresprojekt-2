package de.hhbk.jahresprojekt.model;

import java.util.Date;

public class File {
    private Long id;
    private String path;
    private Date date;

    public File() {
    }

    public File(Long id, String path, Date date) {
        this.id = id;
        this.path = path;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
