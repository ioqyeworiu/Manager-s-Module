package com.library.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.library.controller.AddProductController;
import com.library.model.LibraryModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

public class AddProductFrm extends JFrame {
    private JLabel productIdLbl;
    private JLabel nameLbl;
    private JLabel catLbl;
    private JLabel priceLbl;
    private JLabel taxLbl;
    private JLabel discntLbl;
    private JLabel providerLbl;
    private JLabel unitLbl;
    private JLabel libIdLbl;

    private JTextField productIdTxtF;
    private JTextField nameTxtF;
    private JTextField catTxtF;
    private JTextField priceTxtF;
    private JTextField taxTxtF;
    private JTextField discntTxtF;
    private JTextField providerTxtF;
    private JTextField untiTxtF;
    private JComboBox libIdComB;
    private JButton saveBtn;
    private JButton cancelBtn;

    public AddProductFrm(ArrayList<LibraryModel> libraries) {
        AddProductController addProductController = new AddProductController(this);

        setTitle("Add Product");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        //product id 
        JPanel productIdPanel = new JPanel(new BorderLayout(10, 10));
        productIdLbl = new JLabel("Product ID:");
        productIdTxtF = new JTextField(50);
        productIdLbl.setLabelFor(productIdTxtF);
        productIdPanel.add(productIdLbl, BorderLayout.NORTH);
        productIdPanel.add(productIdTxtF, BorderLayout.CENTER);

        //product name
        JPanel namPanel = new JPanel(new BorderLayout(10, 10));
        nameLbl = new JLabel("Product Name:");
        nameTxtF = new JTextField(50);
        nameLbl.setLabelFor(nameTxtF);
        namPanel.add(nameLbl, BorderLayout.NORTH);
        namPanel.add(nameTxtF, BorderLayout.CENTER);

        //product Category
        JPanel catPanel = new JPanel(new BorderLayout(10, 10));
        catLbl = new JLabel("Category:");
        catTxtF = new JTextField(50);
        catLbl.setLabelFor(catTxtF);
        catPanel.add(catLbl, BorderLayout.NORTH);
        catPanel.add(catTxtF, BorderLayout.CENTER);

        //product price
        JPanel pricePanel = new JPanel(new BorderLayout(10, 10));
        priceLbl = new JLabel("Price:");
        priceTxtF = new JTextField(50);
        priceLbl.setLabelFor(priceTxtF);
        pricePanel.add(priceLbl, BorderLayout.NORTH);
        pricePanel.add(priceTxtF, BorderLayout.CENTER);

        //product tax
        JPanel taxPanel = new JPanel(new BorderLayout(10, 10));
        taxLbl = new JLabel("Tax:");
        taxTxtF = new JTextField(50);
        taxLbl.setLabelFor(taxTxtF);
        taxPanel.add(taxLbl, BorderLayout.NORTH);
        taxPanel.add(taxTxtF, BorderLayout.CENTER);

        //product discount
        JPanel discntPanel = new JPanel(new BorderLayout(10, 10));
        discntLbl = new JLabel("Discount:");
        discntTxtF = new JTextField(50);
        discntLbl.setLabelFor(discntTxtF);
        discntPanel.add(discntLbl, BorderLayout.NORTH);
        discntPanel.add(discntTxtF, BorderLayout.CENTER);

        //provider
        JPanel providerPanel = new JPanel(new BorderLayout(10, 10));
        providerLbl = new JLabel("Provider:");
        providerTxtF = new JTextField(50);
        providerLbl.setLabelFor(providerTxtF);
        providerPanel.add(providerLbl, BorderLayout.NORTH);
        providerPanel.add(providerTxtF, BorderLayout.CENTER);

        //unit
        JPanel unitPanel = new JPanel(new BorderLayout(10, 10));
        unitLbl = new JLabel("Unit:");
        untiTxtF = new JTextField(50);
        unitLbl.setLabelFor(untiTxtF);
        unitPanel.add(unitLbl, BorderLayout.NORTH);
        unitPanel.add(untiTxtF, BorderLayout.CENTER);

        //library
        String[] libIdAndName = new String[libraries.size()];
        for (int i = 0; i < libraries.size(); i++) {
            libIdAndName[i] = "ID = " + libraries.get(i).getID() + " => " + libraries.get(i).getName();
        }
        JPanel libPanel = new JPanel(new BorderLayout(10, 10));
        libIdLbl = new JLabel("Library:");
        libIdComB = new JComboBox(libIdAndName);
        libIdLbl.setLabelFor(libIdComB);
        libPanel.add(libIdLbl, BorderLayout.NORTH);
        libPanel.add(libIdComB, BorderLayout.CENTER);

        //save/ cancel button
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(addProductController);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(addProductController);
        southPanel.add(saveBtn);
        southPanel.add(cancelBtn);

        //add component to window
        JPanel centerPanel = new JPanel(new GridLayout(9, 1));
        centerPanel.add(productIdPanel);
        centerPanel.add(namPanel);
        centerPanel.add(catPanel);
        centerPanel.add(pricePanel);
        centerPanel.add(taxPanel);
        centerPanel.add(discntPanel);
        centerPanel.add(providerPanel);
        centerPanel.add(unitPanel);
        centerPanel.add(libPanel);

        JScrollPane centerScrollPane = new JScrollPane(centerPanel);

        add(centerScrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JLabel getProductIdLbl() {
        return productIdLbl;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }

    public JLabel getCatLbl() {
        return catLbl;
    }

    public JLabel getPriceLbl() {
        return priceLbl;
    }

    public JLabel getTaxLbl() {
        return taxLbl;
    }

    public JLabel getDiscntLbl() {
        return discntLbl;
    }

    public JLabel getProviderLbl() {
        return providerLbl;
    }

    public JLabel getUnitLbl() {
        return unitLbl;
    }

    public JLabel getLibIdLbl() {
        return libIdLbl;
    }

    public JTextField getProductIdTxtF() {
        return productIdTxtF;
    }

    public JTextField getNameTxtF() {
        return nameTxtF;
    }

    public JTextField getCatTxtF() {
        return catTxtF;
    }

    public JTextField getPriceTxtF() {
        return priceTxtF;
    }

    public JTextField getTaxTxtF() {
        return taxTxtF;
    }

    public JTextField getDiscntTxtF() {
        return discntTxtF;
    }

    public JTextField getProviderTxtF() {
        return providerTxtF;
    }

    public JTextField getUntiTxtF() {
        return untiTxtF;
    }

    public JComboBox getLibIdComB() {
        return libIdComB;
    }

}
