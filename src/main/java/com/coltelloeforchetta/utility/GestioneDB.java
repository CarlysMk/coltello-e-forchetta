package com.coltelloeforchetta.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;


public class GestioneDB {

    static String url = "jdbc:sqlite:data/Utenti.db";
    private static final double EARTH_RADIUS_KM = 6371.0; // Raggio medio della Terra in km
    public GestioneDB() {

    }

    public Boolean checkUsername(String toFind) {
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

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
    /**
     * Trova i ristoranti nelle vicinanze dell'utente in base alla latitudine e longitudine.
     *
     * @param userLat La latitudine dell'utente.
     * @param userLon La longitudine dell'utente.
     * @param maxDistance La distanza massima in km per considerare un ristorante "vicino".
     * @return Una lista di nomi di ristoranti nelle vicinanze.
     */
    public static List<String> findRestaurantsNear(double userLat, double userLon, double maxDistance) {
        List<String> nearbyRestaurants = new ArrayList<>();
        String sql = "SELECT Name, Latitude, Longitude FROM ristoranti";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("Name");
                double lat = rs.getDouble("Latitude");
                double lon = rs.getDouble("Longitude");

                double distance = calculateDistance(userLat, userLon, lat, lon);

                if (distance <= maxDistance) {
                    nearbyRestaurants.add(name + " - Distanza: " + distance + " km");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nearbyRestaurants;
    }

    public static String[] getUser(String username) {
        String sql = "SELECT * FROM utente WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new String[]{
                    rs.getString("username"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("dataNascita"),
                    rs.getString("ruolo")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Se l'utente non esiste
    }

}



