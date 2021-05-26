package de.hhbk.jahresprojekt.model;

import java.util.Date;

public class File {
    private int id;
    private String path;
    private Date date;

    public File() {
    }

    public File(int id, String path, Date date) {
        this.id = id;
        this.path = path;
        this.date = date;
    }

    public int getId() {
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
