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

    public class Utente {
        private String nomeUtente, cognomeUtente, username, ClearPassword, dataNascita, ruolo;
        private String[] domicilio = new String[3];

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

        // Getter e Setter per ClearPassword
        public String getClearPassword() {
            return ClearPassword;
        }

        public void setClearPassword(String clearPassword) {
            this.ClearPassword = clearPassword;
        }

        // Getter e Setter per dataNascita
        public String getDataNascita() {
            return dataNascita;
        }

        public void setDataNascita(String dataNascita) {
            this.dataNascita = dataNascita;
        }

        // Getter e Setter per ruolo
        public String getRuolo() {
            return ruolo;
        }

        public void setRuolo(String ruolo) {
            this.ruolo = ruolo;
        }

        // Getter e Setter per domicilio
        public String[] getDomicilio() {
            return domicilio;
        }

        public void setDomicilio(String[] domicilio) {
            this.domicilio = domicilio;
        }
    }

}
