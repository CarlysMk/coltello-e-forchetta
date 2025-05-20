package com.coltelloeforchetta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.coltelloeforchetta.models.Utente;
import com.coltelloeforchetta.utility.GestioneFile;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestioneFile fileUtenti = new GestioneFile("data/Utenti.txt");
        
        int scelta;
        String nome, cognome, username, password, dataNascita, ruolo;
        Utente utente; 

        String url = "jdbc:sqlite:coltello_e_forchetta.db";

        String sql = "CREATE TABLE IF NOT EXISTS utente (\n"
                + " username integer PRIMARY KEY,\n"
                + " nome text NOT NULL,\n"
                + " cognome text NOT NULL,\n"
                + " password text NOT NULL,\n"
                + " dataNascita text,\n"
                + " ruolo text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabella 'utente' creata con successo!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//commento con shift + option + a 
        while (true) { 
            System.out.println("----------------------------------" + 
                                "\nBenvenuto in The Knife" + 
                                "\n1. Login" + 
                                "\n2. Registrazione utente" +
                                "\n3. Guest" +
                                "\n4. Test" +
                                "\n0. Esci" +
                                "\n----------------------------------" +
                                "\nScegli un'opzione: ");

            if (sc.hasNextInt()) {
                scelta = sc.nextInt();
                sc.nextLine(); 

                switch (scelta) {
                    case 1:
                        System.out.println("Login\n" + 
                            "----------------------------------");

                        System.out.println("inserisci username");
                        username = sc.nextLine();
                        System.out.println("inserisci password");
                        password = sc.nextLine(); 

                        utente = new Utente(username, password);

                        break;
                        
                    case 2:
                        System.out.println("Registrazione nuovo utente");

                                System.out.println("Inserisci nome");
                                nome = sc.nextLine();
                                System.out.println("Inserisci cognome");
                                cognome = sc.nextLine();
                                System.out.println("Inserisci username");
                                username = sc.nextLine();

                                if (fileUtenti.cercaMatch(username, 2)) {
                                    System.out.println("Username già esistente, riprova");
                                    break;
                                } else {
                                    System.out.println("Username disponibile");
                                    
                                }

                                System.out.println("Inserisci password");
                                password = sc.nextLine();
                                System.out.println("Inserisci data di nascita"); // da rendere facoltativa e da controllare l'input... 
                                dataNascita = sc.nextLine();

                                System.out.println("Sei un ristoratore? (1. Si / 2. No)");

                                switch (Integer.parseInt(sc.nextLine())) {
                                    case 1:
                                        ruolo = "ristoratore";
                                        break;
                                    case 2:
                                        ruolo = "utente";
                                        break;
                                    default:
                                        System.out.println("Opzione non valida");
                                        continue;
                                }

                                utente = new Utente(nome, cognome, username, password, dataNascita, ruolo);
                                System.out.println("Registrazione completata!");
                                System.out.println("Benvenuto " + utente.getUsername());
                                System.out.println("Effettua nuovamente il login dalla tab di inizio");
                                break;
                            
                    case 3:
                        System.out.println("Accesso come Guest!");
                        break;

                    case 4:
                        String [] temp = fileUtenti.getCoords("data/Ristoranti.txt", "Jungsik New York");
                        if (temp != null) {
                            System.out.println("Latitudine: " + temp[0]);
                            System.out.println("Longitudine: " + temp[1]);
                        } else {
                            System.out.println("Coordinate non trovate");
                        }
                        break;
                        
                    case 0:
                        System.out.println("Uscita dal programma...");
                        sc.close();
                        return; 
                    default:
                        System.out.println("Opzione non valida");
                }
            } else {
                System.out.println("Errore: Inserisci un numero valido!");
                sc.nextLine(); 
            }
        } 
    } 
} 

