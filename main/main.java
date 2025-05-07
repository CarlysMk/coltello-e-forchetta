import java.util.Scanner;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int scelta;

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
                    break;
                case 2:
                    System.out.println("Registrazione nuovo utente");
                    break;
                case 3:
                    System.out.println("Accesso come Guest!");
                    break;
                case 0:
                    System.out.println("Uscita dal programma...");
                    sc.close();
                    return; 
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        } else {
            System.out.println("Errore: Inserisci un numero valido!");
            sc.nextLine(); 
        }
    }
}

