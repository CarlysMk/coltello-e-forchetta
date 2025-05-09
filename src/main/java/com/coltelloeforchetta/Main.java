package com.coltelloeforchetta;

import java.util.Scanner;

import com.coltelloeforchetta.models.Utente;
import com.coltelloeforchetta.utility.GestioneFile;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestioneFile fileUtenti = new GestioneFile("data/Utenti.txt");
        
        int scelta;
        String nome, cognome, username, password, dataNascita, ruolo;
        Utente utente; 


//commento con shift + option + a 
        while (true) { 
            System.out.println("----------------------------------" + 
                                "\nBenvenuto in The Knife" + 
                                "\n1. Login" + 
                                "\n2. Registrazione utente" +
                                "\n3. Guest" +
                                "\n0. Esci" +
                                "\n----------------------------------" +
                                "\nScegli un'opzione: ");

            if (sc.hasNextInt()) {
                scelta = sc.nextInt();
                sc.nextLine(); 

                switch (scelta) {
                    case 1:
                        System.out.println("Login" + 
                                "\n1. Login Utente" +
                                "\n2. Login Ristoratore" + 
                                "\n----------------------------------");

                        switch (Integer.parseInt(sc.nextLine())) {
                            case 1:
                                System.out.println("Login Utente");
                                System.out.println("inserisci username");
                                username = sc.nextLine();
                                System.out.println("inserisci password");
                                password = sc.nextLine(); 

                                

                                break;
                            case 2:
                                System.out.println("Login Ristoratore");
                                System.out.println("inserisci username");
                                username = sc.nextLine();
                                System.out.println("inserisci password");
                                password = sc.nextLine();
                                break;
                            default:
                                System.out.println("Opzione non valida");
                        }
                        break;
                    case 2:
                        System.out.println("Registrazione nuovo utente");
                        System.out.println("1. Registrazione Utente");
                        System.out.println("2. Registrazione Ristoratore");
                        switch (Integer.parseInt(sc.nextLine())) {
                            case 1:
                                System.out.println("Inserisci nome");
                                nome = sc.nextLine();
                                System.out.println("Inserisci cognome");
                                cognome = sc.nextLine();
                                System.out.println("Inserisci username");
                                username = sc.nextLine();
                                System.out.println("Inserisci password");
                                password = sc.nextLine();
                                System.out.println("Inserisci data di nascita"); // da rendere facoltativa e da controllare l'input... 
                                dataNascita = sc.nextLine();
                                ruolo = "utente";
                                utente = new Utente(nome, cognome, username, password, dataNascita, ruolo);
                                System.out.println("Registrazione completata!");
                                System.out.println("Benvenuto " + utente.getUsername());
                                System.out.println("Effettua nuovamente il login dalla tab di inizio");
                                break;
                            case 2:
                                System.out.println("Inserisci nome");
                                nome = sc.nextLine();
                                System.out.println("Inserisci cognome");
                                cognome = sc.nextLine();
                                System.out.println("Inserisci username");
                                username = sc.nextLine();
                                System.out.println("Inserisci password");
                                password = sc.nextLine();
                                System.out.println("Inserisci data di nascita"); // da rendere facoltativa
                                dataNascita = sc.nextLine();
                                ruolo = "ristoratore";
                                utente = new Utente(nome, cognome, username, password, dataNascita, ruolo);
                                System.out.println("Registrazione completata!");
                                System.out.println("Benvenuto " + utente.getUsername());
                                System.out.println("Effettua nuovamente il login dalla tab di inizio");
                                break;
                            default:
                                throw new AssertionError();
                        }
                        break;
                    case 3:
                        System.out.println("Accesso come Guest!");
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

