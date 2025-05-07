package com.library.model;

public class InvoiceAssociateProductModel {
    private Integer invoiceID;
    private Integer productID;
    private Integer quantity;

    public InvoiceAssociateProductModel() {
    }

    public InvoiceAssociateProductModel(Integer invoiceID, Integer productID, Integer quantity) {
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
