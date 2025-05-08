package com.library.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.library.controller.AddInvoiceController;
import com.library.controller.AddProductController;

public class AddInvoiceFrm extends JFrame {

    private JLabel idLbl;
    private JLabel dateOfCreationLbl;
    private JLabel productLbl;
    private JLabel paymentMethodLbl;
    private JTextField idTxtF;
    private JTable productTbl;
    private JButton addProductBtn;
    private JComboBox paymentMethodComBox;
    private JButton saveBtn;
    private JButton cancelBtn;
    private DefaultTableModel productTblModel;

    public AddInvoiceFrm() {
        AddInvoiceController addInvoiceController = new AddInvoiceController(this);
        AddProductController addProductController = new AddProductController(this);

        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));
        setTitle("Add Invoice");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // id
        idLbl = new JLabel("Invoice ID:");
        idLbl.setLabelFor(idTxtF);
        idTxtF = new JTextField();

        // products
        productLbl = new JLabel("Products:");
        productLbl.setLabelFor(productTbl);
        addProductBtn = new JButton("New Product");
        addProductBtn.addActionListener(addInvoiceController);
        addProductBtn.addActionListener(addProductController);
        productTblModel = new DefaultTableModel();
        productTbl = new JTable(productTblModel);

        productTblModel.addColumn("Product ID");
        productTblModel.addColumn("Product Name");
        productTblModel.addColumn("Category");
        productTblModel.addColumn("Price");
        productTblModel.addColumn("Tax");
        productTblModel.addColumn("Discount");
        productTblModel.addColumn("Provider");
        productTblModel.addColumn("Unit");
        productTblModel.addColumn("Library");

        // align data to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 1; i < productTbl.getColumnCount(); i++) {
            productTbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // payment methods
        paymentMethodLbl = new JLabel("Payment Method:");
        paymentMethodLbl.setLabelFor(paymentMethodComBox);
        paymentMethodComBox = new JComboBox<String>(
                new String[] { "--Select Payment Method--", "COD", "Master Card", "Debit Card" });
        paymentMethodComBox.setEditable(false);
        add(paymentMethodComBox, BorderLayout.CENTER);
        // save/cancel button
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(addInvoiceController);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(addInvoiceController);

        // add component to window
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JPanel product = new JPanel(new BorderLayout(10, 10));
        product.add(productLbl, BorderLayout.NORTH);
        JScrollPane productTbScrollPane = new JScrollPane(productTbl);
        product.add(productTbScrollPane, BorderLayout.CENTER);
        product.add(addProductBtn, BorderLayout.SOUTH);
        centerPanel.add(product);

        JPanel idPanel = new JPanel(new BorderLayout(10, 10));
        idPanel.add(idLbl, BorderLayout.NORTH);
        idPanel.add(idTxtF, BorderLayout.CENTER);
        centerPanel.add(idPanel);

        JPanel paymentMethod = new JPanel(new BorderLayout(10, 10));
        paymentMethod.add(paymentMethodLbl, BorderLayout.NORTH);
        paymentMethod.add(paymentMethodComBox, BorderLayout.CENTER);
        centerPanel.add(paymentMethod);

        JScrollPane centerScrollPane = new JScrollPane(centerPanel);
        centerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        southPanel.add(saveBtn);
        southPanel.add(cancelBtn);

        add(centerScrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void reload(AddProductFrm addProductFrm) {
        System.out.println(addProductFrm.getProductIdTxtF().getText());
        productTblModel.addRow(new Object[] { addProductFrm.getProductIdTxtF().getText(),
                addProductFrm.getNameTxtF().getText(),
                addProductFrm.getCatTxtF().getText(), addProductFrm.getPriceTxtF().getText(),
                addProductFrm.getTaxTxtF().getText(), addProductFrm.getDiscntTxtF().getText(),
                addProductFrm.getProviderTxtF().getText(),
                addProductFrm.getUntiTxtF().getText(), (String) addProductFrm.getLibIdComB().getSelectedItem() });
    }

    public JTextField getIdTxtF() {
        return idTxtF;
    }

    public JLabel getProductLbl() {
        return productLbl;
    }

    public JLabel getPaymentMethodLbl() {
        return paymentMethodLbl;
    }

    public JTable getProductTbl() {
        return productTbl;
    }

    public JButton getAddProductBtn() {
        return addProductBtn;
    }

    public JComboBox getPaymentMethodComBox() {
        return paymentMethodComBox;
    }

}
