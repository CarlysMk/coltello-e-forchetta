/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *
 * Sede: Como
 ***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.common;
import org.mindrot.jbcrypt.BCrypt;

public class cifratura {

    /**
     * Cifra il testo passato come parametro
     * @param plainTxtPsw stringa in chiaro da cifrare
     * @return cifratura della stringa passata come parametro
     */
    public String cifra(String plainTxtPsw) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(plainTxtPsw, salt);
    }

    /**
     * Verifica che il testo in chiaro corrisponda al proprio hash
     * @param plainTxtPsw stringa in chiaro
     * @param hash hash di una stringa
     * @return true se l'hash corrisponde alla stringa in chiaro, false altrimenti
     */
    public boolean verifica(String plainTxtPsw, String hash) {
        return BCrypt.checkpw(plainTxtPsw, hash);
    }
}

