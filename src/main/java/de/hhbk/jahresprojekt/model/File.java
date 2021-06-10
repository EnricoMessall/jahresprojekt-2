package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class File {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @TableField
    private Long id;
    @TableField
    private String path;
    @TableField
    private Timestamp date;

    public File() {
    }

    public File(Long id, String path, Date date) {
        this.id = id;
        this.path = path;
        this.date = new Timestamp(date.getTime());
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
        this.date = new Timestamp(date.getTime());
    }

    @Override
    public String toString() {
        return path + " (Date: " + date + ")";
    }
}
