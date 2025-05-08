public class Utente {
    
    private String nome, cognome, username, ClearPassword, dataNascita, ruolo;
    private String [] domicilio = new String[3];
    boolean boolRuolo; // TRUE per ristoratore, FALSE per utente


    public Utente(String nome, String cognome, String username, String psw, String dataNascita, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ClearPassword = psw;
        this.dataNascita = dataNascita;
        this.ruolo = ruolo;
        if (ruolo.equals("ristoratore")) {
            this.boolRuolo = true;
        } else if (ruolo.equals("utente")) {
            this.boolRuolo = false;
        }
    }


    
  /*   GestioneFile file = new GestioneFile("data/Utenti.txt");
    file.scriviSuFile("Utente4", true);
    file.leggiDaFile(); */


    public String getUsername() {
        return username;
    }

    public String getRuolo(){
        return ruolo;
    }

    
}
