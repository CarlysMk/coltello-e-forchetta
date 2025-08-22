/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.common;

public class ristorante {
    String nome, nazione, città, IDristorante, fasciaPrezzo;
    int latitudine, longitudine;
    boolean prenotazioneOnline, delivery;
    String[] indirizzo;

    /**
     * Costruttore della classe ristorante.
     *
     * @param nome               Nome del ristorante
     * @param nazione            Nazione in cui si trova
     * @param città              Città in cui si trova
     * @param IDristorante       Identificativo univoco del ristorante
     * @param fasciaPrezzo       Fascia di prezzo
     * @param latitudine         Latitudine geografica
     * @param longitudine        Longitudine geografica
     * @param prenotazioneOnline True se è disponibile la prenotazione online
     * @param delivery           True se è disponibile il servizio di consegna
     * @param indirizzo          Array contenente le informazioni dell'indirizzo
     */

    public ristorante(String nome, String nazione, String città, String IDristorante, String fasciaPrezzo, int latitudine, int longitudine, boolean prenotazioneOnline, boolean delivery, String[] indirizzo){
        this.nome = nome;
        this.nazione = nazione;
        this.città = città;
        this.IDristorante = IDristorante;
        this.fasciaPrezzo = fasciaPrezzo;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.prenotazioneOnline = prenotazioneOnline;
        this.delivery = delivery;
        this.indirizzo = indirizzo;
    }

    /** @return Nome del ristorante */
    public String getNome() { return nome; }

    /** @param nome Imposta il nome del ristorante */
    public void setNome(String nome) { this.nome = nome; }

    /** @return Nazione del ristorante */
    public String getNazione() { return nazione; }

    /** @param nazione Imposta la nazione del ristorante */
    public void setNazione(String nazione) { this.nazione = nazione; }

    /** @return Città del ristorante */
    public String getCittà() { return città; }

    /** @param città Imposta la città del ristorante */
    public void setCittà(String città) { this.città = città; }

    /** @return ID univoco del ristorante */
    public String getIDristorante() { return IDristorante; }

    /** @param IDristorante Imposta l'ID del ristorante */
    public void setIDristorante(String IDristorante) { this.IDristorante = IDristorante; }

    /** @return Fascia di prezzo del ristorante */
    public String getFasciaPrezzo() { return fasciaPrezzo; }

    /** @param fasciaPrezzo Imposta la fascia di prezzo */
    public void setFasciaPrezzo(String fasciaPrezzo) { this.fasciaPrezzo = fasciaPrezzo; }

    /** @return Latitudine del ristorante */
    public int getLatitudine() { return latitudine; }

    /** @param latitudine Imposta la latitudine */
    public void setLatitudine(int latitudine) { this.latitudine = latitudine; }

    /** @return Longitudine del ristorante */
    public int getLongitudine() { return longitudine; }

    /** @param longitudine Imposta la longitudine */
    public void setLongitudine(int longitudine) { this.longitudine = longitudine; }

    /** @return True se è disponibile la prenotazione online */
    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }

    /** @param prenotazioneOnline Imposta la disponibilità della prenotazione online */
    public void setPrenotazioneOnline(boolean prenotazioneOnline) { this.prenotazioneOnline = prenotazioneOnline; }

    /** @return True se è disponibile il servizio di delivery */
    public boolean isDelivery() { return delivery; }

    /** @param delivery Imposta la disponibilità del servizio di delivery */
    public void setDelivery(boolean delivery) { this.delivery = delivery; }

    /** @return Array con le informazioni dell'indirizzo */
    public String[] getIndirizzo() { return indirizzo; }

    /** @param indirizzo Imposta l'indirizzo del ristorante */
    public void setIndirizzo(String[] indirizzo) { this.indirizzo = indirizzo; }

}
