package com.coltelloeforchetta.models;

import org.mindrot.jbcrypt.BCrypt;

import com.coltelloeforchetta.utility.GestioneFile;  

public class Utente {
    

    private String nome, cognome, username, ClearPassword, dataNascita, ruolo;
    private String [] domicilio = new String[3];
    private String HashPsw;
    boolean boolRuolo; // TRUE per ristoratore, FALSE per utente
    private GestioneFile file = new GestioneFile("data/Utenti.txt");

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
        file.scriviSuFile(nome + "-" + cognome + "-" + username + "-" + HashPsw + "-" + dataNascita + "-" + ruolo, true);

    }

    //costruttore per login utente
    public Utente(String username, String psw){
        this.username = username;
        this.ClearPassword = psw;
        if (login(username, psw)) {
            System.out.println("Login effettuato con successo");
        } else {
            System.out.println("Login fallito");
        }
        
        String[] dati = file.getRiga(username, 2);
        if (dati == null) {
            System.out.println("Utente non trovato");
            return;
            
        }else {
            this.nome = dati[0];
            this.cognome = dati[1];
            this.dataNascita = dati[4];
            this.ruolo = dati[5];
            if (this.ruolo.equals("ristoratore")) {
                this.boolRuolo = true;
            } else if (this.ruolo.equals("utente")) {
                this.boolRuolo = false;
            }
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

    

    private boolean login(String username, String password) {
        if(file.cercaMatch(username, 2)){
            if (file.cercaMatch(password, 3)) {
                return true;
            } else {
                System.out.println("Password errata");
                return false;
            }
        }else {
            System.out.println("Username errato");
            return false;
        }
    }

}
