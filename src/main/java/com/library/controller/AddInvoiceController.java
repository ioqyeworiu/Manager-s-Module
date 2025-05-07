package com.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.library.dao.InvoiceAssociateProductDAO;
import com.library.dao.InvoiceDAO;
import com.library.dao.LibraryDAO;
import com.library.model.InvoiceAssociateProductModel;
import com.library.model.InvoiceModel;
import com.library.view.AddInvoiceFrm;
import com.library.view.AddProductFrm;

public class AddInvoiceController implements ActionListener {

    private AddInvoiceFrm addInvoiceFrm;
    private InvoiceDAO invoiceDAO;
    private LibraryDAO libraryDAO;
    private InvoiceAssociateProductDAO invoiceAssociateProductDAO;

    public AddInvoiceController(AddInvoiceFrm addInvoiceFrm) {
        this.addInvoiceFrm = addInvoiceFrm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New Product":
                libraryDAO = new LibraryDAO();
                addInvoiceFrm.dispose();
                new AddProductFrm(libraryDAO.getAllLibraries());
                break;

            case "Save":
                invoiceAssociateProductDAO = new InvoiceAssociateProductDAO();
                for (int i = 0; i < addInvoiceFrm.getProductTbl().getRowCount(); i++) {
                    invoiceAssociateProductDAO.save(
                            new InvoiceAssociateProductModel(Integer.parseInt(addInvoiceFrm.getIdTxtF().getText()),
                                    Integer.parseInt((String) addInvoiceFrm.getProductTbl().getValueAt(i, 0)),
                                    10));
                }

                invoiceDAO = new InvoiceDAO();
                //
                InvoiceModel invoiceModel = new InvoiceModel();
                invoiceModel.setID(Integer.parseInt(addInvoiceFrm.getIdTxtF().getText()));
                invoiceModel.setPaymentMethod((String) addInvoiceFrm.getPaymentMethodComBox().getSelectedItem());
                invoiceModel.setTax((float) 0);
                invoiceModel.setUserID(1);
                if (invoiceDAO.saveInvoice(invoiceModel)) {
                    JOptionPane.showMessageDialog(addInvoiceFrm, "Add invoice successfully");
                } else {
                    JOptionPane.showMessageDialog(addInvoiceFrm, "Invoice ID has already existed!");
                }

                //
                addInvoiceFrm.dispose();
                break;

            case "Cancel":
                addInvoiceFrm.dispose();
                break;

            default:
                break;
        }

    }

}
