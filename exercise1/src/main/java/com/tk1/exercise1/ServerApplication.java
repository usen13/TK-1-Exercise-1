package com.tk1.exercise1;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApplication {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ServerRemote server = new Server();
        Registry registry = LocateRegistry.createRegistry(1888);
        registry.bind("server", server);
        System.out.println("Server ready");
    }
}
