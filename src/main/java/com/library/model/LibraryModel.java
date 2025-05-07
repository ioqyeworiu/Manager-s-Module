package com.library.model;

public class LibraryModel {
    private Integer ID;
    private String name;
    private String address;
    private String description;

    public LibraryModel() {
    }

    public LibraryModel(Integer iD, String name, String address, String description) {
        ID = iD;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
