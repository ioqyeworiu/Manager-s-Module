package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.library.model.InvoiceAssociateProductModel;

public class InvoiceAssociateProductDAO {
    private Connection con;
    private Statement stmt;
    private PreparedStatement preStmt;
    private ResultSet rs;

    public InvoiceAssociateProductDAO() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=ManagerModule;TrustServerCertificate=true",
                    "NMCNPM_Manager",
                    "123456");
            System.out.println("ket noi thanh cong toi " + con.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean save(InvoiceAssociateProductModel invoiceAssociateProductModels) {
        try {
            preStmt = con.prepareStatement(
                    "INSERT INTO Invoice_Product"
                            + "(InvoiceID, ProductID, Quantity)\n"
                            + "VALUES\n"
                            + "(?, ?, ?)");

            preStmt.setInt(1, invoiceAssociateProductModels.getInvoiceID());
            preStmt.setInt(2, invoiceAssociateProductModels.getProductID());
            preStmt.setInt(3, invoiceAssociateProductModels.getQuantity());
            preStmt.executeUpdate();
            preStmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
