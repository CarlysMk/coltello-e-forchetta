/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.server.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class queryExecutor {
    /**
     * Controlla l'esistenza del database.
     * @param conn connessione al database Postgres generico.
     * @param db_name nome del database.
     * @return true se il database esiste, false altrimenti.
     */
    public boolean checkDBExists(Connection conn, String db_name){
        Statement stmt = null;
        ResultSet rset = null;

        try {

            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT datname FROM pg_database;");

            while(rset.next()){
                String dbname = rset.getString(1);
                if(dbname.equals(db_name)) {
                    return true;
                }
            }

            rset.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    // ---DDL QUERY---  

    /**
     * Esegue una query DDL.
     * @param conn connessione al database.
     * @param query query DDL da eseguire.
     * @throws SQLException se si verifica un errore
     * di accesso al database o se il metodo
     * è chiamato su una connessione chiusa.
     */
    private void genericDDLquery(Connection conn, String query) throws SQLException{
        Statement stmt = null;
        
        stmt = conn.createStatement();
        stmt.executeUpdate(query);

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Crea il database senza tabelle usando come input un file.
     * @param conn connessione al database Postgres generico.
     * @param file file contenente il codice sql usato per la creazione del database.
     */
    public void createBDByFile(Connection conn, FileReader file){

        System.out.println("Database inesistente\nCreazione di un nuovo database");

        try {
            BufferedReader reader = new BufferedReader(file);
            String line;
            StringBuilder sb = new StringBuilder();
            String query;

            while((line = reader.readLine()) != null){
                sb.append(line);
            }

            query = sb.toString();

            genericDDLquery(conn, query);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore nella lettura del file");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella creazione del database");
        }
    }

    /**
     * Crea le tabelle del database usando come input un file.
     * @param conn connessione al database.
     * @param file file contenente il codice sql usato per la creazione dellle tabelle del database.
     */
    public void createTablesByFile(Connection conn, String file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();
            String query;

            while((line = reader.readLine()) != null){
                sb.append(line);
            }

            query = sb.toString();

            genericDDLquery(conn, query);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore nella lettura del file");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nella creazione delle tabelle");
        }
    }

    // ---INSERT QUERY---   

    // TODO modificare insert
    // TODO per ricordare: questo metodo va bene
    /**
     * Popola la tabella Libri usando il contenito di un file CSV
     * @param conn connessione al database
     * @param file file contenente i dati che verranno inseriti nel database. 
     * Il file deve utilizzare come separatore il carattere "," e deve contenere le colonne
     * "title", "authors" e "publish date (year)". Le colonne possono essere ordinate in 
     * qualunque modo, ma devono essere presenti nel file.
     */
    public void populateLibriByCSV(Connection conn, String file, int quantity){
        try {
            //String CSVSplit = ",";
            String CSVSplit = ",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String query = "INSERT INTO Libri (Titolo,Autore,AnnoPubblicazione) VALUES (?,?,?)";
            int cont = 0;

            // indici per determinare quale colonna del file contiene titolo, autore e anno di pubblicazione
            int indexOfTitolo = -1;
            int indexOfAutore = -1;
            int indexOfAnnoPubblicazione = -1;

            line = reader.readLine();
            String[] colonne = line.split(CSVSplit);

            for(int i = 0; i < colonne.length; i++){
                colonne[i] = colonne[i].toLowerCase();
                if(colonne[i].equals("title")){
                    indexOfTitolo = i;
                }
                if(colonne[i].equals("authors")){
                    indexOfAutore = i;
                }
                if(colonne[i].equals("publish date (year)")){
                    indexOfAnnoPubblicazione = i;
                }
            }

            if(indexOfTitolo == -1 || indexOfAutore == -1 || indexOfAnnoPubblicazione == -1){
                System.out.println("Il file " + file + " non è adatto");
            }

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(query);
            while((line = reader.readLine()) != null ){
                String[] valori = line.split(CSVSplit);
                //titolo
                stmt.setString(1, valori[indexOfTitolo]);
                //autore
                stmt.setString(2, valori[indexOfAutore]);
                //anno di pubblicazione
                stmt.setInt(3, Integer.parseInt(valori[indexOfAnnoPubblicazione]));

                cont++;                
                stmt.executeUpdate();
                if(cont == quantity){
                    break;
                }
            }

            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            reader.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    // TODO fare insert per tutte le tabelle

    // ---SELECT QUERY---

    // TODO testare questo metodo
    /**
     * Interroga il database eseguendo una query 
     * di SELECT, ritornando il risultato della query.
     * @param conn connessione al database.
     * @param query query SQL contente il comando SELECT
     * @return un arraylist i cui elementi rappresentano 
     * ciascuno un record del risultato della query.
     * I singoli campi di ciascun record sono separati 
     * dal carattere ','.
     * @throws SQLException se si verifica un errore
     * di accesso al database o se il metodo
     * è chiamato su una connessione chiusa.
     */
    private ArrayList<String> genericSELECTquery(Connection conn, String query) throws SQLException{

        ArrayList<String> result = new ArrayList<String>();
        Statement stmt=null;
		
        stmt= conn.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        //TODO solo per test, rimuovere
        System.out.println(query);
        while(rs.next()){
            String row="";
            int numCol=rs.getMetaData().getColumnCount();
            for(int i=1;i<=numCol;i++){
                int type=rs.getMetaData().getColumnType(i);
                switch(type){
                case java.sql.Types.BIGINT:
                case java.sql.Types.INTEGER:
                case java.sql.Types.SMALLINT:
                case java.sql.Types.TINYINT:
                case java.sql.Types.NUMERIC:
                    row = row + rs.getLong(i);
                    break;
                case java.sql.Types.FLOAT:
                    row = row + rs.getFloat(i);
                    break;
                case java.sql.Types.DOUBLE:
                    row = row + rs.getDouble(i);
                    break;
                case java.sql.Types.DATE:
                    row = row + rs.getDate(i).toString();
                    break;
                case java.sql.Types.VARCHAR:
                case java.sql.Types.CHAR:
                    row = row + rs.getString(i);
                    break;
                case java.sql.Types.TIMESTAMP:
                    row = row + rs.getTimestamp(i).toString();
                    break;
                }

                if(i != numCol){
                    row = row + ",";
                }
            }
            //TODO solo per test, rimuovere
            System.out.println(row);
            result.add(row);
        }
		
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		
        return result;
    }


    // TODO testare questo metodo
    /**
     * Inserisce nella tabella OperatoriAutorizzati
     * un nuovo record rappresentato dall'oggetto
     * passato come secondo parametro.
     * Nel campo "Centro" verrà inserito valore
     * {@code NULL}.
     * <P><B>Nota:</B> usare questo metodo quando
     * l'operatore non è associato a nessun centro. 
     * Per aggiungere il centro usare il metodo ...
     * @param conn connessione al database.
     * @param r oggetto della classe Registrazione
     * i cui campi verranno usati come parametri per
     * la query di inserimento.
     * @throws SQLException se si verifica un errore
     * di accesso al database o se il metodo
     * è chiamato su una connessione chiusa.
     */
    /* 
    public void populateOperatoriAutorizzati(Connection conn, Registrazione r) throws SQLException{

        if(!r.isCorrect()){
            System.err.println("Errore: oggetto non è corretto");
            return;
        }
        
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement(sqlDMLStatement.insert_OperatoriAutorizzati);

        //nome
        stmt.setString(1, r.toStrings()[0]);
        //cognome
        stmt.setString(2, r.toStrings()[1]);
        //codice fiscale
        stmt.setString(3, r.toStrings()[2]);
        //email
        stmt.setString(4, r.toStrings()[3]);
        //password
        stmt.setString(5, r.toStrings()[4]);
        //centro
        //stmt.setString(6, null);
        stmt.setNull(6, java.sql.Types.VARCHAR);
        
        stmt.executeUpdate();

        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
        */
}