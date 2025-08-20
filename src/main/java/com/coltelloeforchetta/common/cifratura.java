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

        public static String cifra(String plainTxtPsw) {
            String salt = BCrypt.gensalt(12);
            return BCrypt.hashpw(plainTxtPsw, salt);
        }

        public static boolean verifica(String plainTxtPsw, String hash) {
            return BCrypt.checkpw(plainTxtPsw, hash);
        }
    }

