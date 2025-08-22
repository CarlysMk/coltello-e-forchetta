/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.common;

/**
 * Classe che rappresenta un utente dell'applicazione, con dati personali,
 * credenziali e ruolo (utente o ristoratore).
 */


public class utente {

    private String nomeUtente, cognomeUtente, username, plainTxtPsw, dataNascita, ruolo;
    private String [] domicilio = new String[3];
    private String HashedPsw;
    boolean isRistoratore;
    private cifratura cif = new cifratura();

    /**
     * Costruttore per la registrazione di un nuovo utente.
     *
     * @param nome         Nome dell'utente
     * @param cognome      Cognome dell'utente
     * @param username     Nome utente per il login
     * @param plainTxtPsw  Password in chiaro
     * @param dataNascita  Data di nascita dell'utente
     * @param ruolo        Ruolo dell'utente ("ristoratore" o "utente")
     */

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

    /**
     * Costruttore per il login di un utente esistente.
     *
     * @param username     Nome utente per il login
     * @param plainTxtPsw  Password in chiaro
     */

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

    /** @return Nome dell'utente */
    public String getNomeUtente() {
        return nomeUtente;
    }

    /** @param nomeUtente Imposta il nome dell'utente */
    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    /** @return Cognome dell'utente */
    public String getCognomeUtente() {
        return cognomeUtente;
    }

    /** @param cognomeUtente Imposta il cognome dell'utente */
    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }

    /** @return Username dell'utente */
    public String getUsername() {
        return username;
    }

    /** @param username Imposta lo username dell'utente */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return Password in chiaro dell'utente */
    public String getplainTxtPsw() {
        return plainTxtPsw;
    }

    /**
     * Imposta la password in chiaro e aggiorna la versione cifrata.
     *
     * @param plainTxtPsw Password in chiaro
     */
    public void setplainTxtPsw(String plainTxtPsw) {
        this.plainTxtPsw = plainTxtPsw;
        this.HashedPsw = cif.cifra(plainTxtPsw);
    }

    /** @return Data di nascita dell'utente */
    public String getDataNascita() {
        return dataNascita;
    }

    /** @param dataNascita Imposta la data di nascita dell'utente */
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * @return True se l'utente è un ristoratore, false se è un utente normale
     */
    public boolean getRuolo() {
        return isRistoratore;
    }

    /**
     * Imposta il ruolo dell'utente.
     *
     * @param ruolo True se ristoratore, false se utente
     */
    public void setRuolo(Boolean ruolo) {
        this.isRistoratore = ruolo;
    }

    /** @return Array contenente le informazioni sul domicilio dell'utente */
    public String[] getDomicilio() {
        return domicilio;
    }

    /** @param domicilio Imposta il domicilio dell'utente */
    public void setDomicilio(String[] domicilio) {
        this.domicilio = domicilio;
    }

    /** @return Password cifrata dell'utente */
    public String getHashedPsw() {
        return HashedPsw;
    }

    /** @param HashedPsw Imposta direttamente la password cifrata */
    public void setHashedPsw(String HashedPsw) {
        this.HashedPsw = HashedPsw;
    }

}
