package de.hhbk.jahresprojekt.model;

import de.hhbk.jahresprojekt.views.annotations.TableField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Frederik Hafemann
 * @author Enrico Messall
 */
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @TableField
    private Long id;
    @TableField
    private int value;
    @TableField
    private String comment;

    public Item() {
    }

    public Item(Long id, int value, String comment) {
        this.id = id;
        this.value = value;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.join(", ", String.valueOf(id), getComment(), String.valueOf(getValue()));
    }
}
