package com.coltelloeforchetta.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestioneDB {
    public GestioneDB() {

    }

    public Boolean checkUsername(String toFind) {
        String url = "jdbc:sqlite:coltello_e_forchetta.db";
        String sql = "SELECT * FROM utente WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, toFind);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true; // L'username esiste già
            } else {
                return false; // L'username non esiste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Se l'utente non è trovato
    }
}



