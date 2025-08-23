/**************************************
 * Matricola    Cognome     Nome
 * 755152       Paredi      Giacomo
 *              
 * Sede: Como
***************************************/
// TODO aggiungere le matricole di tutti

package com.coltelloeforchetta.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.coltelloeforchetta.common.serverTKRemote;
import com.coltelloeforchetta.server.database.DBConnection;

public class serverTK implements serverTKRemote {

    static int PORT = 1234;

    public serverTK(){
        // TODO spostare qua la creazione dell'oggetto DBConnection + aggiungere eventuali opzioni di setup
    }

    public static void main(String[] args)  {
        //DBConnection db = new DBConnection();


        serverTK server = new serverTK();
        serverTKRemote stub = null;
        
        try {
            stub = (serverTKRemote) UnicastRemoteObject.exportObject(server, PORT);
        } catch (RemoteException e) {   //eccezione -> porta occupata o porta illegale
            System.out.println("Porta già occupata");
            System.out.println(e);
            System.exit(-1);
        }

        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(PORT);
        } catch (RemoteException e) {
            System.out.println("Impossibile esportare il registry");
            System.out.println(e);
            System.exit(-2);
        }

        try {
            registry.bind("ServerTK", stub);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (AlreadyBoundException e) {
            System.out.println(e);
            System.exit(-3);
        }

        System.out.println( "Server started" );
    }


    // TODO fare parte di RMI

    @Override
    public void metodo() throws RemoteException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'metodo'");
        System.out.println("server risponde");
    }

    @Override
    public int metodo2(int a) throws RemoteException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'metodo2'");
        return a*a;
    }
    

    
}
