package com.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.library.dao.InvoiceDAO;
import com.library.view.ManageImportFrm;
import com.library.view.ManagerHomeFrm;
import com.library.view.ManagerViewReportFrm;

public class ManagerHomeController implements ActionListener {
    
    private ManagerHomeFrm managerHomeFrm;

    public ManagerHomeController(ManagerHomeFrm managerHomeFrm) {
        this.managerHomeFrm = managerHomeFrm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Manage Import":
                new ManageImportFrm(new InvoiceDAO().getAllInvoices());
                break;
        
            case "View Report":
                new ManagerViewReportFrm();
                break;
        }
    }

}
