/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.common;

public class utente {

    private String nomeUtente, cognomeUtente, username, ClearPassword, dataNascita, ruolo;
    private String [] domicilio = new String[3];
    private String HashPsw;
    boolean boolRuolo; // TRUE per ristoratore, FALSE per utente

    //costruttore per la REGISTRAZIONE di un nuovo utente
    public utente(String nome, String cognome, String username, String psw, String dataNascita, String ruolo) {
        this.nomeUtente = nome;
        this.cognomeUtente = cognome;
        this.username = username;
        this.ClearPassword = psw;
        this.dataNascita = dataNascita;
        this.ruolo = ruolo;
        if (this.ruolo.equals("ristoratore")) {
            this.boolRuolo = true;
        } else if (this.ruolo.equals("utente")) {
            this.boolRuolo = false;
        }
        //TODO registrare nel DB il nuovo utente
        //ricordarsi di cifrare la psw prima di caricarla nel DB
    }

    //costruttore per LOGIN utente
    public utente(String username, String psw){
        this.username = username;
        this.ClearPassword = psw;

        //TODO controllo/login DB
//        if (db.login(username, psw)) {
//            System.out.println("Login effettuato con successo");
//        } else {
//            System.out.println("Login fallito");
//        }

        //TODO ottenere i dati completi dal DB e popolare i dati locali
    }

    

}
