package com.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.library.dao.InvoiceDAO;
import com.library.view.AddInvoiceFrm;
import com.library.view.ManageImportFrm;

public class ManagerImportController implements ActionListener {

    private ManageImportFrm manageImportFrm;
    private InvoiceDAO invoiceDAO;

    public ManagerImportController(ManageImportFrm manageImportFrm) {
        this.manageImportFrm = manageImportFrm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Search":
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(
                        (DefaultTableModel) manageImportFrm.getInvoiceTable().getModel());
                manageImportFrm.getInvoiceTable().setRowSorter(sorter);

                String keyword = manageImportFrm.getSearchField().getText().trim();
                if (keyword.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
                }
                break;

            case "Add Invoice":
                new AddInvoiceFrm();
                invoiceDAO = new InvoiceDAO();
                manageImportFrm.reloadInvoiceTbl(invoiceDAO.getAllInvoices());
                break;

            case "Delete Invoice":
                JTable ivTbl = manageImportFrm.getInvoiceTable();
                ArrayList<Integer> invoiceIDs = new ArrayList<>();

                for (int i = 0; i < ivTbl.getRowCount(); i++) {
                    if ((boolean) ivTbl.getValueAt(i, 0)) {
                        Integer ivID = (Integer) ivTbl.getValueAt(i, 1);
                        invoiceIDs.add(ivID);

                    }
                }

                if (invoiceIDs.size() == 0) {
                    JOptionPane.showMessageDialog(ivTbl, "No invoice selected, please choose one!");
                    break;
                }

                invoiceDAO = new InvoiceDAO();
                if (invoiceDAO.deleteInvoices(invoiceIDs)) {
                    JOptionPane.showMessageDialog(manageImportFrm, "Delete successfully");

                    // reload data
                    invoiceDAO = new InvoiceDAO();
                    manageImportFrm.reloadInvoiceTbl(invoiceDAO.getAllInvoices());
                }
                break;

            default:

                break;
        }
    }

}
