package com.coltelloeforchetta.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestioneFile {
    private String percorsoRelativo;

    public GestioneFile(String percorsoRelativo) {
        this.percorsoRelativo = percorsoRelativo;
    }

    public void scriviSuFile(String testo, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoRelativo, append))) {
            writer.write(testo);
            writer.newLine();
            System.out.println("Scrittura completata su: " + percorsoRelativo);
        } catch (IOException e) {
            System.out.println("Errore di scrittura: " + e.getMessage());
        }
    }

    public void leggiDaFile() {
        Path path = Paths.get(percorsoRelativo);

        if (!Files.exists(path)) {
            System.out.println("Errore: Il file '" + percorsoRelativo + "' non esiste!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(percorsoRelativo))) {
            String linea;
            System.out.println("\nContenuto di " + percorsoRelativo + ":");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Errore di lettura: " + e.getMessage());
        }
    }


    private String[] getAllRows(){
        ArrayList<String> righe = new ArrayList<>();
        Path path = Paths.get(percorsoRelativo);
        if (!Files.exists(path)) {
            System.out.println("Errore: Il file '" + percorsoRelativo + "' non esiste!");
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(percorsoRelativo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                righe.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Errore di lettura: " + e.getMessage());
        }
        String[] righeArray = new String[righe.size()];
        for (int i = 0; i < righe.size(); i++) {
            righeArray[i] = righe.get(i);
        }
        return righeArray;
    }

    public boolean cercaMatch(String match, int colonna) {
        String[] righe = getAllRows();
        if (righe == null) {
            return false;
        }
        for (String riga : righe) {
            if (riga.split("-")[colonna].equals(match)) {
                return true;
            }
        }
        return false;
    }  

    //come il medoto cercaMatch ma restituisce il valore specifico ricercato
    public String getMatch(String match, int colonna) {
        String[] righe = getAllRows();
        if (righe == null) {
            return null;
        }
        for (String riga : righe) {
            String ret = riga.split("-")[colonna];
            if (ret.equals(match)) {
                return ret;
            }
        }
        return null;
    }  

    private int getRowIndex(String match, int colonna) {
        String[] righe = getAllRows();
        if (righe == null) {
            return -1;
        }
        for (int i = 0; i < righe.length; i++) {
            if (righe[i].split("-")[colonna].equals(match)) {
                return i;
            }
        }
        return -1;
    }

    public String[] getRiga(){
        String[] righe = getAllRows();
        if (righe == null) {
            return null;
        }
        String[] riga = righe[getRowIndex(righe[0], 0)].split("-");
        return riga;    
    }

}



