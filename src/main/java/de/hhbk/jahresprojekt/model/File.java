package de.hhbk.jahresprojekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class File {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
