package com.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.library.dao.ProductDAO;
import com.library.model.ProductModel;
import com.library.view.AddInvoiceFrm;
import com.library.view.AddProductFrm;

public class AddProductController implements ActionListener {

    private AddProductFrm addProductFrm;
    private AddInvoiceFrm addInvoiceFrm;
    private ProductDAO productDAO;

    public AddProductController(AddProductFrm addProductFrm) {
        this.addProductFrm = addProductFrm;
    }

    public AddProductController(AddInvoiceFrm addInvoiceFrm) {
        this.addInvoiceFrm = addInvoiceFrm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Save":
                productDAO = new ProductDAO();
                ProductModel productModel = new ProductModel();
                try {
                    productModel.setID(Integer.parseInt(addProductFrm.getProductIdTxtF().getText().trim()));
                    productModel.setName(addProductFrm.getNameTxtF().getText().trim());
                    productModel.setCategory(addProductFrm.getCatTxtF().getText().trim());
                    productModel.setPrice(Float.parseFloat(addProductFrm.getPriceTxtF().getText().trim()));
                    productModel.setTax(Float.parseFloat(addProductFrm.getTaxTxtF().getText().trim()));
                    productModel.setDiscount(Float.parseFloat(addProductFrm.getDiscntTxtF().getText().trim()));
                    productModel.setProvider(addProductFrm.getProviderTxtF().getText().trim());
                    productModel.setUnit(addProductFrm.getUntiTxtF().getText().trim());
                    String libID = ((String) addProductFrm.getLibIdComB().getSelectedItem()).trim();
                    productModel.setLibraryID(
                            Integer.parseInt(libID.split(" ")[2]));
                } catch (Exception er) {
                    er.printStackTrace();
                    JOptionPane.showMessageDialog(addProductFrm,
                            "Please fill in completely and exactly. Check:\n"
                                    + "Product ID: integer\n" + "Product Name: String\n" + "Category: String\n"
                                    + "Price: Float\n" + "Tax: Float\n" + "Discount: Float\n"
                                    + "Provider: String\n" + "Unit: String\n" + "Library ID: Integer",
                            "Save fail", 0);
                    break;
                }
                if (productDAO.saveProducts(productModel)) {
                    addInvoiceFrm = new AddInvoiceFrm();
                    addInvoiceFrm.reload(addProductFrm);
                    addProductFrm.dispose();
                } else {
                    JOptionPane.showMessageDialog(addProductFrm,
                            "The ID = " + productModel.getID() + " has already existed", "Save fail", 0);
                }
                break;

            case "Cancel":
                addProductFrm.dispose();
                break;
            default:
                break;
        }
    }

}
