package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.library.model.InvoiceModel;

public class InvoiceDAO {
    private Connection con;
    private Statement stmt;
    private PreparedStatement preStmt;
    private ResultSet rs;

    private InvoiceModel invoiceModel;

    public InvoiceDAO() {
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

    public ArrayList<InvoiceModel> getAllInvoices() {
        ArrayList<InvoiceModel> invoices = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT IV.*, SUM(P.Price*IVP.Quantity*(1-P.Discount/100)) AS TotalMoney, U.Email\n" + //
                            "FROM Invoices AS IV\n" + //
                            "JOIN Users AS U ON IV.UserID = U.ID\n" + //
                            "JOIN Invoice_Product AS IVP ON IV.ID = IVP.InvoiceID\n" + //
                            "JOIN Products AS P ON IVP.ProductID = P.ID\n" + //
                            "GROUP BY IV.ID, IV.DateOfCreation, IV.Tax, IV.PaymentMethod, IV.UserID, U.Email\n" + //
                            "ORDER BY DateOfCreation DESC;");

            while (rs.next()) {
                invoiceModel = new InvoiceModel();
                invoiceModel.setID(rs.getInt("ID"));
                invoiceModel.setDateOfCreation(rs.getDate("DateOfCreation"));
                invoiceModel.setTotalMoney(rs.getFloat("TotalMoney"));
                invoiceModel.setTax(rs.getFloat("Tax"));
                invoiceModel.setPaymentMethod(rs.getString("PaymentMethod"));
                invoiceModel.setCreator(rs.getString("Email"));
                invoices.add(invoiceModel);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public boolean deleteInvoices(ArrayList<Integer> invoiceIDs) {
        try {
            preStmt = con.prepareStatement("DELETE FROM Invoices " + "WHERE ID = ?");
            for (Integer ivID : invoiceIDs) {
                preStmt.setInt(1, ivID);
                preStmt.executeUpdate();
            }
            preStmt.close();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveInvoice(InvoiceModel invoice) {
        try {
            preStmt = con.prepareStatement(
                    "INSERT INTO Invoices"
                            + "(ID, Tax, PaymentMethod, UserID)\n"
                            + "VALUES\n"
                            + "(?, ?, ?, ?)");
            preStmt.setInt(1, invoice.getID());
            preStmt.setFloat(2, invoice.getTax());
            preStmt.setString(3, invoice.getPaymentMethod());
            preStmt.setInt(4, invoice.getUserID());
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
