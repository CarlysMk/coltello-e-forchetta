/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.server.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
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
        try (Scanner in = new Scanner(System.in)) {
            //System.out.print("Inserire url del database: ");
            //db_url = in.nextLine();
            db_url = "jdbc:postgresql://localhost:5432/";

            //System.out.print("Inserire nome del database: ");
            //db_name = in.nextLine();
            db_name = "the_knife_db";

            //System.out.print("Inserire username: ");
            //username = in.nextLine();
            username = "postgres";

            //System.out.print("Inserire password: ");
            //password = in.nextLine();
            password = "root";
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
                try {
                    qexe.createBDByFile(tempconn, new FileReader("src\\main\\java\\com\\coltelloeforchetta\\server\\database\\createDB.sql"));
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("File non trovato");
                }
            }
            
            if(tempconn != null){
                tempconn.close();
            }

            System.out.println("Connessione al database " + db_url + db_name +"\nNome: " + username + "\nPassword : " + password);
            db_conn = DriverManager.getConnection(db_url + db_name, username, password);

            // creazioni tabelle

            if(!db_exists){        
                qexe.createTablesByFile(db_conn,"src\\main\\java\\com\\coltelloeforchetta\\server\\database\\createTables.sql");
                populateDB(qexe);
            }


            // TODO aggiungere i popolamenti per tutte le tabelle
            // fare metodo populateDB contenente i singoli metodi per popolare le tabelle


            //popolamento tabella libri

            qexe.populateLibriByCSV(db_conn,"src\\main\\java\\com\\example\\sql_statement\\BooksDatasetClean.csv",100);

            

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
    
    private void populateDB(queryExecutor qexe){
        qexe.populateUtentiRegistratiByCSV(conn, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\utentiRegistrati.csv");
        qexe.populateRistorantiByCSV(conn, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\ristoranti.csv");
        qexe.populateCategorieCucineByCSV(conn, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\categorieCucine.csv");
        qexe.populateRecensioniByCSV(conn, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\recensioni.csv");
        qexe.populateSpecializzazioniRistoranteByCSV(conn, "src\\main\\java\\com\\coltelloeforchetta\\server\\database\\populate_examples\\specializzazioniRistoranti.csv");
        qexe.populateRistorantiPreferitiByCSV(conn, );
    }
    
}