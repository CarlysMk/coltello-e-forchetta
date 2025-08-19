package com.coltelloeforchetta;

import java.util.Scanner;

import com.coltelloeforchetta.common.utente;
import com.coltelloeforchetta.utility.GestioneDB;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int scelta;
        String nome, cognome, username, password, dataNascita, ruolo;
        utente utente;
        GestioneDB db = new GestioneDB();
        

//commento con shift + option + a 
        while (true) { 
            System.out.println("----------------------------------" + 
                                "\nBenvenuto in The Knife" + 
                                "\n1. Login" + 
                                "\n2. Registrazione utente" +
                                "\n3. Guest" +
                                "\n4. Test" +
                                "\n0. Esci" +
                                "\n----------------------------------" +
                                "\nScegli un'opzione: ");

            if (sc.hasNextInt()) {
                scelta = sc.nextInt();
                sc.nextLine(); 

                switch (scelta) {
                    case 1:
                        System.out.println("Login\n" + 
                            "----------------------------------");

                        System.out.println("inserisci username");
                        username = sc.nextLine();
                        System.out.println("inserisci password");
                        password = sc.nextLine(); 

                        utente = new utente(username, password);

                        break;
                        
                    case 2:
                        System.out.println("Registrazione nuovo utente");

                                System.out.println("Inserisci nome");
                                nome = sc.nextLine();
                                System.out.println("Inserisci cognome");
                                cognome = sc.nextLine();
                                System.out.println("Inserisci username");
                                username = sc.nextLine();

                                if (db.checkUsername(username)) {
                                    System.out.println("Username già esistente, riprova");
                                    break;
                                } else {
                                    System.out.println("Username disponibile");
                                    
                                }

                                System.out.println("Inserisci password");
                                password = sc.nextLine();
                                System.out.println("Inserisci data di nascita"); // da rendere facoltativa e da controllare l'input... 
                                dataNascita = sc.nextLine();

                                System.out.println("Sei un ristoratore? (1. Si / 2. No)");

                                switch (Integer.parseInt(sc.nextLine())) {
                                    case 1:
                                        ruolo = "ristoratore";
                                        break;
                                    case 2:
                                        ruolo = "utente";
                                        break;
                                    default:
                                        System.out.println("Opzione non valida");
                                        continue;
                                }

                                utente = new utente(nome, cognome, username, password, dataNascita, ruolo);
                                System.out.println("Registrazione completata!");
                                System.out.println("Benvenuto " + utente.getUsername());
                                System.out.println("Effettua nuovamente il login dalla tab di inizio");
                                break;
                            
                    case 3:
                        System.out.println("Accesso come Guest!");
                        break;

                    case 4:
                        break;
                        
                    case 0:
                        System.out.println("Uscita dal programma...");
                        sc.close();
                        return; 
                    default:
                        System.out.println("Opzione non valida");
                }
            } else {
                System.out.println("Errore: Inserisci un numero valido!");
                sc.nextLine(); 
            }
        } 
    } 
} 

