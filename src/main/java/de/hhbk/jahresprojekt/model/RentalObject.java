package de.hhbk.jahresprojekt.model;

import java.util.List;

public class RentalObject {
    private Long id;
    private String objectNumber;
    private String objectDescription;
    private RentalType rentalType;
    private boolean commercial;
    private Adress adress;
    private int livingSpace;
    private int squareMeterPrice;
    private int additionalCosts;
    private String notes;
    private List<RentalObject> subObjects;

    public RentalObject(){}

    public RentalObject(Long id, String objectNumber, String objectDescription, RentalType rentalType, boolean commercial, Adress adress, int livingSpace, int squareMeterPrice, int additionalCosts, String notes, List<RentalObject> subObjects) {
        this.id = id;
        this.objectNumber = objectNumber;
        this.objectDescription = objectDescription;
        this.rentalType = rentalType;
        this.commercial = commercial;
        this.adress = adress;
        this.livingSpace = livingSpace;
        this.squareMeterPrice = squareMeterPrice;
        this.additionalCosts = additionalCosts;
        this.notes = notes;
        this.subObjects = subObjects;
    }

    public Long getId() {
        return id;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public boolean isCommercial() {
        return commercial;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public int getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(int livingSpace) {
        this.livingSpace = livingSpace;
    }

    public int getSquareMeterPrice() {
        return squareMeterPrice;
    }

    public void setSquareMeterPrice(int squareMeterPrice) {
        this.squareMeterPrice = squareMeterPrice;
    }

    public int getAdditionalCosts() {
        return additionalCosts;
    }

    public void setAdditionalCosts(int additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<RentalObject> getSubObjects() {
        return subObjects;
    }

    public void setSubObjects(List<RentalObject> subObjects) {
        this.subObjects = subObjects;
    }
}
