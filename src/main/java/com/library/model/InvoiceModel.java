package com.library.model;

import java.sql.Date;

public class InvoiceModel {
    private Integer ID;
    private Date DateOfCreation;
    private Float totalMoney;
    private Float tax;
    private String paymentMethod;
    private String creator;
    private Integer UserID;

    public InvoiceModel() {
    }

    public InvoiceModel(Integer iD, Date dateOfCreation, Float totalMoney, Float tax,
            String paymentMethod, String creator, Integer userID) {
        ID = iD;
        this.DateOfCreation = dateOfCreation;
        this.totalMoney = totalMoney;
        this.tax = tax;
        this.paymentMethod = paymentMethod;
        this.creator = creator;
        this.UserID = userID;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public Date getDateOfCreation() {
        return DateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        DateOfCreation = dateOfCreation;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

}
