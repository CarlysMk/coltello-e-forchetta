/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.common;

public class utente {

    private String nomeUtente, cognomeUtente, username, plainTxtPsw, dataNascita, ruolo;
    private String [] domicilio = new String[3];
    private String HashedPsw;
    boolean isRistoratore;
    private cifratura cif = new cifratura();

    //costruttore per la REGISTRAZIONE di un nuovo utente
    public utente(String nome, String cognome, String username, String plainTxtPsw, String dataNascita, String ruolo) {
        this.nomeUtente = nome;
        this.cognomeUtente = cognome;
        this.username = username;
        this.plainTxtPsw = plainTxtPsw;
        this.dataNascita = dataNascita;
        this.ruolo = ruolo;
        if (this.ruolo.equals("ristoratore")) {
            this.isRistoratore = true;
        } else if (this.ruolo.equals("utente")) {
            this.isRistoratore = false;
        }
        HashedPsw = cif.cifra(plainTxtPsw);
        //TODO registrare nel DB il nuovo utente
    }

    //costruttore per LOGIN utente
    public utente(String username, String plainTxtPsw){
        this.username = username;
        this.plainTxtPsw = plainTxtPsw;
        HashedPsw = cif.cifra(plainTxtPsw);

        //TODO controllo/login DB
//        if (db.login(username, psw)) {
//            System.out.println("Login effettuato con successo");
//        } else {
//            System.out.println("Login fallito");
//        }

        //TODO ottenere i dati completi dal DB e popolare i dati locali
    }

        // Getter e Setter per nomeUtente
        public String getNomeUtente() {
            return nomeUtente;
        }

        public void setNomeUtente(String nomeUtente) {
            this.nomeUtente = nomeUtente;
        }

        // Getter e Setter per cognomeUtente
        public String getCognomeUtente() {
            return cognomeUtente;
        }

        public void setCognomeUtente(String cognomeUtente) {
            this.cognomeUtente = cognomeUtente;
        }

        // Getter e Setter per username
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // Getter e Setter per plainTxtPsw
        public String getplainTxtPsw() {
            return plainTxtPsw;
        }

        public void setplainTxtPsw(String plainTxtPsw) {
            this.plainTxtPsw = plainTxtPsw;
            this.HashedPsw = cif.cifra(plainTxtPsw);
        }

        // Getter e Setter per dataNascita
        public String getDataNascita() {
            return dataNascita;
        }

        public void setDataNascita(String dataNascita) {
            this.dataNascita = dataNascita;
        }

        // Getter e Setter per ruolo
        public boolean getRuolo() {
            return isRistoratore;
        }

        public void setRuolo(Boolean ruolo) {
            this.isRistoratore = ruolo;
        }

        // Getter e Setter per domicilio
        public String[] getDomicilio() {
            return domicilio;
        }

        public void setDomicilio(String[] domicilio) {
            this.domicilio = domicilio;
        }

        //Getter per HashedPsw
        public String getHashedPsw() {
            return HashedPsw;
        }

        public void setHashedPsw(String HashedPsw) {
            this.HashedPsw = HashedPsw;
        }
}
