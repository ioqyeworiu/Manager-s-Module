package com.library.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.library.controller.ManagerHomeController;

public class ManagerHomeFrm extends JFrame {
    private JButton manageImportButton;
    private JButton viewReporButton;

    public ManagerHomeFrm() {
        ManagerHomeController managerHomeController = new ManagerHomeController(this);
        // main window
        setVisible(true);
        setLayout(new GridLayout(2, 1, 30, 30));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Manger Home");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // manage import button
        manageImportButton = new JButton("Manage Import");
        manageImportButton.setSize(300, 300);
        manageImportButton.addActionListener(managerHomeController);

        // view report button
        viewReporButton = new JButton("View Report");
        viewReporButton.setSize(300, 300);
        viewReporButton.addActionListener(managerHomeController);

        // add buttons to window
        add(manageImportButton);
        add(viewReporButton);
    }

}
