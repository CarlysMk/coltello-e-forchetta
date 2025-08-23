package com.coltelloeforchetta.server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.coltelloeforchetta.common.serverTKRemote;

public class clientTest {
    static int PORT = 1234;

    public static void main(String[] args) {

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
        } catch (RemoteException e) {
            System.out.println(e);
            System.exit(-1);
        }

        serverTKRemote stub = null;
        try {
            stub = (serverTKRemote) registry.lookup("ServerTK");
        } catch (RemoteException e) {
            System.out.println(e);
            System.exit(-2);
        } catch (NotBoundException e) {
            System.out.println(e);
            System.exit(-3);
        }

        System.out.println( "Client started" );


        try {
            stub.metodo();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            int a = stub.metodo2(3);
            System.out.println(a);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
