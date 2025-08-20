package com.coltelloeforchetta.common;

public class TestMain {
        public static void main(String[] args) {
            String password = "prova";
            cifratura c = new cifratura();

            utente u = new utente("utente", password);

            if(c.verifica(password,c.cifra(u.getplainTxtPsw())))
                System.out.println("ok");
        }
    }
