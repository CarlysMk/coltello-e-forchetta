package com.coltelloeforchetta.models;

import org.mindrot.jbcrypt.BCrypt;

import com.coltelloeforchetta.utility.GestioneDB;

public class Utente {
    

    private String nome, cognome, username, ClearPassword, dataNascita, ruolo;
    private String [] domicilio = new String[3];
    private String HashPsw;
    boolean boolRuolo; // TRUE per ristoratore, FALSE per utente
    private GestioneDB db = new GestioneDB();

    //costruttore per registrazione utente
    public Utente(String nome, String cognome, String username, String psw, String dataNascita, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ClearPassword = psw;
        this.dataNascita = dataNascita;
        this.ruolo = ruolo;
        if (this.ruolo.equals("ristoratore")) {
            this.boolRuolo = true;
        } else if (this.ruolo.equals("utente")) {
            this.boolRuolo = false;
        }
        HashPsw = BCrypt.hashpw(psw, BCrypt.gensalt());
        db.addUser(username, nome, cognome, HashPsw, dataNascita, ruolo);
    }

    //costruttore per login utente
    public Utente(String username, String psw){
        this.username = username;
        this.ClearPassword = psw;
        if (db.login(username, psw)) {
            System.out.println("Login effettuato con successo");
        } else {
            System.out.println("Login fallito");
        }
        String[] dati = db.getUser(username);
            this.nome = dati[1];
            this.cognome = dati[2];
            this.dataNascita = dati[4];
            this.ruolo = dati[5];
            if (dati[5].equals("ristoratore")) {
                this.boolRuolo = true;
            } else if (this.ruolo.equals("utente")) {
                this.boolRuolo = false;
            }
        
        
        
    }


    /* //controllo psw
    boolean match = BCrypt.checkpw("password123", hashedPassword);
        System.out.println("La password corrisponde? " + match); */

    
  /*   GestioneFile file = new GestioneFile("data/Utenti.txt");
    file.scriviSuFile("Utente4", true);
    file.leggiDaFile(); */


    public String getUsername() {
        return username;
    }

    public String getRuolo(){
        return ruolo;
    }
    

}
