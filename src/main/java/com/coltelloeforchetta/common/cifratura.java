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

    String hashedString;

    public String cifratura(String s){
        return hashedString = BCrypt.hashpw(s, BCrypt.gensalt());
    }

    public boolean controllo(String plainTextPassword, String storedHash){
        boolean passwordMatches = BCrypt.checkpw(plainTextPassword, storedHash);
        return passwordMatches;
    }

}
