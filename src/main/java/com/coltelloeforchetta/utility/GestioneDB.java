package com.coltelloeforchetta.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;


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

    public static void addUser(String username, String nome, String cognome, String password, String dataNascita, String ruolo) {
        String url = "jdbc:sqlite:coltello_e_forchetta.db";

        String sql = "INSERT INTO utente (username, nome, cognome, password, dataNascita, ruolo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, nome);
            pstmt.setString(3, cognome);
            pstmt.setString(4, password);
            pstmt.setString(5, dataNascita != null ? dataNascita : null);
            pstmt.setString(6, ruolo);

            pstmt.executeUpdate();
            System.out.println("Utente aggiunto con successo!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean login(String username, String password) {
        String url = "jdbc:sqlite:coltello_e_forchetta.db";
        String sql = "SELECT password FROM utente WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(password, hashedPassword); // Verifica password hashata
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Se l'utente non esiste o la password è errata
    }

}



