package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.library.model.ProductModel;

public class ProductDAO {
    private Connection con;
    private Statement stmt;
    private PreparedStatement preStmt;
    private ResultSet rs;

    public ProductDAO() {
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

    public ProductModel getProductByID(Integer productId) {
        try {
            ProductModel productModel = new ProductModel();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Products WHERE ID = " + productId);
            productModel.setID(rs.getInt("ID"));
            productModel.setName(rs.getString("Name"));
            productModel.setCategory(rs.getString("Category"));
            productModel.setPrice(rs.getFloat("Price"));
            productModel.setTax(rs.getFloat("Tax"));
            productModel.setDiscount(rs.getFloat("Discount"));
            productModel.setProvider(rs.getString("Provider"));
            productModel.setUnit(rs.getString("Unit"));
            productModel.setLibraryID(rs.getInt("LibraryID"));
            rs.close();
            stmt.close();
            con.close();
            return productModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public ArrayList<ProductModel> getProductsByInvoice(Integer invoiceID) {
    //     try {
    //         ArrayList<ProductModel> products = new ArrayList<>();
    //         stmt = con.createStatement();
    //         rs = stmt.executeQuery(
    //                 "SELECT prd.* FROM Products AS prd\n"
    //                         + "JOIN Invoice_Product AS iv_prd ON prd.ID = iv_prd.ProductID\n"
    //                         + "WHERE iv_prd.InvoiceID = " + invoiceID);
    //         while (rs.next()) {
    //             ProductModel productModel = new ProductModel();
    //             productModel.setID(rs.getInt("ID"));
    //             productModel.setName(rs.getString("Name"));
    //             productModel.setCategory(rs.getString("Category"));
    //             productModel.setPrice(rs.getFloat("Price"));
    //             productModel.setTax(rs.getFloat("Tax"));
    //             productModel.setDiscount(rs.getFloat("Discount"));
    //             productModel.setProvider(rs.getString("Provider"));
    //             productModel.setUnit(rs.getString("Unit"));
    //             productModel.setLibraryID(rs.getInt("LibraryID"));
    //             products.add(productModel);
    //         }

    //         rs.close();
    //         stmt.close();
    //         con.close();
    //         return products;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    public boolean saveProducts(ArrayList<ProductModel> products) {
        try {
            preStmt = con.prepareStatement(
                    "INSERT INTO Products"
                            + "(ID, Name, Category, Price, Tax, Discount, Provider, Unit, LibraryID)\n"
                            + "VALUES\n"
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (ProductModel product : products) {
                preStmt.setInt(1, product.getID());
                preStmt.setString(2, product.getName());
                preStmt.setString(3, product.getCategory());
                preStmt.setFloat(4, product.getPrice());
                preStmt.setFloat(5, product.getTax());
                preStmt.setFloat(6, product.getDiscount());
                preStmt.setString(7, product.getProvider());
                preStmt.setString(8, product.getUnit());
                preStmt.setInt(9, product.getLibraryID());
                preStmt.executeUpdate();
            }
            preStmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveProducts(ProductModel product) {
        try {
            preStmt = con.prepareStatement(
                    "INSERT INTO Products"
                            + "(ID, Name, Category, Price, Tax, Discount, Provider, Unit, LibraryID)\n"
                            + "VALUES\n"
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preStmt.setInt(1, product.getID());
            preStmt.setString(2, product.getName());
            preStmt.setString(3, product.getCategory());
            preStmt.setFloat(4, product.getPrice());
            preStmt.setFloat(5, product.getTax());
            preStmt.setFloat(6, product.getDiscount());
            preStmt.setString(7, product.getProvider());
            preStmt.setString(8, product.getUnit());
            preStmt.setInt(9, product.getLibraryID());
            preStmt.executeUpdate();
            preStmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteProduct(ArrayList<Integer> productIDs) {
        try {
            preStmt = con.prepareStatement("DELETE FROM Products WHERE ID = ?");
            for (Integer prID : productIDs) {
                preStmt.setInt(1, prID);
                preStmt.executeUpdate();
            }
            preStmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
