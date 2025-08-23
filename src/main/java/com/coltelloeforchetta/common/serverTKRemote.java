package com.coltelloeforchetta.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface serverTKRemote extends Remote{
    void metodo() throws RemoteException;
    int metodo2(int a) throws RemoteException;
}
