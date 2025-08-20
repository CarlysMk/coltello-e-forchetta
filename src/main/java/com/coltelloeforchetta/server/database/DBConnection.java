/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.server.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Connessione col DBMS.
 * @author Giacomo Paredi
 * @version 1.0.0
 */
public class DBConnection {
    // TODO Usare Singleton

    private Connection conn = null;

    private String db_url;

    private String db_name;
    //private String db_name = "the_knife_db";

    private String username;
    //private String username = "postgres";
    private String password;
    //private String password = "root";

    public DBConnection() {
        do {
            this.inserireCredenziali();
        } while (!this.accesso());
    }

    /**
     * Richiede l'inserimento delle credenziali
     */
    public void inserireCredenziali() {
        String accessoRapido;
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("ACCESSO AL DATABASE");
            File credenziali = new File("src\\main\\java\\com\\coltelloeforchetta\\server\\database\\Credenziali.txt");
            if(credenziali.exists() && !credenziali.isDirectory()){
                System.out.print("File 'Credenziali.txt' trovato.\nDigitare 'si' per accedere usando le informazioni contenute nel file, altro per inserire le credenziali manualmente: ");
                accessoRapido = in.nextLine();
                accessoRapido = accessoRapido.toLowerCase();
                if(accessoRapido.equals("si")){
                    accessoConFile(credenziali.getPath());
                }else{
                    accessoManuale();
                }
            }else{
                accessoManuale();
            }
        }
    }

    private void accessoConFile(String path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            db_url = reader.readLine();
            db_name = reader.readLine();
            username = reader.readLine();
            password = reader.readLine();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore nella lettura del file");
        } 
    }

    private void accessoManuale(){
        String salvareFile;
        try (Scanner in = new Scanner(System.in)){

            System.out.println("INSERIRE LE CREDENZIALI DEL DATABASE");
            System.out.print("Inserire url del database: ");
            db_url = in.nextLine();
            //db_url = "jdbc:postgresql://localhost:5432/";

            System.out.print("Inserire nome del database: ");
            db_name = in.nextLine();
            //db_name = "the_knife_db";

            System.out.print("Inserire username: ");
            username = in.nextLine();
            //username = "postgres";

            System.out.print("Inserire password: ");
            password = in.nextLine();
            //password = "root";

            System.out.print("Digitare 'si' per salvare le credenziali inserite e accedere rapidamente le volte successive: ");
            salvareFile = in.nextLine();
            salvareFile.toLowerCase();
            if(salvareFile.equals("si")){
                creaCredenzialiFile();
            }
        }
    }

    private void creaCredenzialiFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\java\\com\\coltelloeforchetta\\server\\database\\Credenziali.txt"));
            writer.append(db_url);
            writer.newLine();
            writer.append(db_name);
            writer.newLine();
            writer.append(username);
            writer.newLine();
            writer.append(password);

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Accede al DataBase
     * @return true se l'accesso è riuscito
     */
    public boolean accesso() {
        conn = openConnection();
        if (conn == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Apre la connessione al database e la ritorna.
     * Nel caso non esistesse:
     * 1) apre una connessione al database Postgres
     * generico;
     * 2) crea il database
     * 3) apre la connessione al database appena
     * creato;
     * 4) crea le tabelle del database;
     * 5) ritorna la connessione al 
     * database.
     * @return un oggetto {@code Connection} che 
     * contiene la connessione al database.
     */
    private Connection openConnection(){

        boolean db_exists = false;
        Connection tempconn = null;
        Connection db_conn = null;
        queryExecutor qexe = new queryExecutor();

        try {
            System.out.println("Connessione al database generico " + db_url +"\nNome: " + username + "\nPassword : " + password);
            tempconn = DriverManager.getConnection(db_url, username, password);
            System.out.println("Controllo esistenza database con nome: " + db_name);
            db_exists = qexe.checkDBExists(tempconn, db_name);
            System.out.println("Esitenza: " + db_exists);

            // creazione DB
            if(!db_exists){
                
                qexe.createDB(tempconn, db_name);
            }
            
            if(tempconn != null){
                tempconn.close();
            }

            System.out.println("Connessione al database " + db_url + db_name +"\nNome: " + username + "\nPassword : " + password);
            db_conn = DriverManager.getConnection(db_url + db_name, username, password);

            // creazioni tabelle
            if(!db_exists){        
                qexe.createTablesByFile(db_conn,"src\\main\\java\\com\\coltelloeforchetta\\server\\database\\createTables.sql");
                populateDB(db_conn, qexe);
            }            
        } catch (SQLException e) {
            System.err.println("SQLState:"+ e.getSQLState());
		    System.err.println("Error code:"+ e.getErrorCode());
		    System.err.println("Message:"+ e.getMessage());
            return null;
        }
        return db_conn;
    }

    public Connection getConnection(){
        return this.conn;
    }
    
    private void populateDB(Connection db_exist, queryExecutor qexe){
        qexe.populateUtentiRegistratiByCSV(db_exist, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\utentiRegistrati.csv");
        qexe.populateRistorantiByCSV(db_exist, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\ristoranti.csv");
        qexe.populateCategorieCucineByCSV(db_exist, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\categorieCucine.csv");
        qexe.populateRecensioniByCSV(db_exist, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\recensioni.csv");
        qexe.populateSpecializzazioniRistoranteByCSV(db_exist, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\specializzazioniRistoranti.csv");
        qexe.populateRistorantiPreferitiByCSV(db_exist, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\ristorantiPreferiti.csv");
    }
    
}