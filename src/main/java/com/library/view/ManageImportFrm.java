package com.library.view;

import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.library.controller.ManagerImportController;
import com.library.model.InvoiceModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class ManageImportFrm extends JFrame {

    DefaultTableModel invoiceTblModel;
    private JTable invoiceTable;
    private JButton addInvoiceBtn;
    private JButton deleteInvoiceBtn;
    private JTextField searchField;
    private JButton searchBtn;

    public ManageImportFrm(ArrayList<InvoiceModel> invoices) {
        ManagerImportController managerImportController = new ManagerImportController(this);

        setTitle("Invoice List");
        setLocationRelativeTo(null);
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // invoice table
        invoiceTblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        invoiceTable = new JTable(invoiceTblModel);
        JScrollPane scrollPane = new JScrollPane(invoiceTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // add/delete/back buttons
        addInvoiceBtn = new JButton("Add Invoice");
        addInvoiceBtn.addActionListener(managerImportController);
        deleteInvoiceBtn = new JButton("Delete Invoice");
        deleteInvoiceBtn.addActionListener(managerImportController);

        // serach box
        searchField = new JTextField(15);
        searchField.setSize(50, 30);
        searchField.setToolTipText("Type something, example: id = 1");
        searchField.setText("1");
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(managerImportController);

        // add columns
        invoiceTblModel.addColumn("Select");
        invoiceTblModel.addColumn("ID");
        invoiceTblModel.addColumn("Date");
        invoiceTblModel.addColumn("Total Money");
        invoiceTblModel.addColumn("Tax");
        invoiceTblModel.addColumn("Payment Method");
        invoiceTblModel.addColumn("Creator");

        // select button
        invoiceTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        invoiceTable.getColumnModel().getColumn(0).setCellRenderer(invoiceTable.getDefaultRenderer(Boolean.class));

        // display data
        for (InvoiceModel ivmd : invoices) {
            invoiceTblModel
                    .addRow(new Object[] {
                            false, ivmd.getID(), ivmd.getDateOfCreation(),
                            ivmd.getTotalMoney(), ivmd.getTax(), ivmd.getPaymentMethod(), ivmd.getCreator() });
        }

        // align data to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 1; i < invoiceTable.getColumnCount(); i++) {
            invoiceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // add components
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Enter key word: "));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        JPanel centerJpanel = new JPanel(new BorderLayout(10, 10));
        centerJpanel.add(addInvoiceBtn, BorderLayout.NORTH);
        centerJpanel.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        southPanel.add(deleteInvoiceBtn);

        add(searchPanel, BorderLayout.NORTH);
        add(centerJpanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JTable getInvoiceTable() {
        return invoiceTable;
    }

    public JButton getAddInvoiceBtn() {
        return addInvoiceBtn;
    }

    public JButton getDeleteInvoiceBtn() {
        return deleteInvoiceBtn;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public void reloadInvoiceTbl(ArrayList<InvoiceModel> invoices) {
        invoiceTblModel.setRowCount(0);
        for (InvoiceModel ivmd : invoices) {
            invoiceTblModel
                    .addRow(new Object[] {
                            false, ivmd.getID(), ivmd.getDateOfCreation(),
                            ivmd.getTotalMoney(), ivmd.getTax(), ivmd.getPaymentMethod(),
                            ivmd.getCreator() });
        }
    }
}
