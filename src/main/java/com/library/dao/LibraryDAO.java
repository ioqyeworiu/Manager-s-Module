package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.library.model.LibraryModel;

public class LibraryDAO {
    private Connection con;
    private Statement stmt;
    private PreparedStatement preStmt;
    private ResultSet rs;

    private LibraryModel libraryModel;

    public LibraryDAO() {
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

    public ArrayList<LibraryModel> getAllLibraries() {
        try {
            ArrayList<LibraryModel> libraries = new ArrayList<>();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Libraries");
            while (rs.next()) {
                libraryModel = new LibraryModel();
                libraryModel.setID(rs.getInt("ID"));
                libraryModel.setName(rs.getString("Name"));
                libraryModel.setAddress(rs.getString("Address"));
                libraryModel.setDescription(rs.getString("Description"));
                libraries.add(libraryModel);
            }
            rs.close();
            stmt.close();
            con.close();
            return libraries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
