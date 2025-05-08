import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int scelta;
        String nome, cognome, username, password, dataNascita, ruolo;
        Utente utente; 


//commento con shift + option + a 
        while (true) { 
            System.out.println("\nBenvenuto in The Knife");
            System.out.println("1. Login");
            System.out.println("2. Registrazione utente");
            System.out.println("3. Guest");
            System.out.println("0. Esci");
            System.out.print("Scegli un'opzione: ");

            if (sc.hasNextInt()) {
                scelta = sc.nextInt();
                sc.nextLine(); 

                switch (scelta) {
                    case 1:
                        System.out.println("Login");
                        System.out.println("1. Login Utente");
                        System.out.println("2. Login Ristoratore");

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

