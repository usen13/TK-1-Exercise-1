package com.tk1.exercise1;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientApplication {

    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        // Issue: https://github.com/javafxports/openjdk-jfx/issues/236
        ClientApplicationWorkAround.main(args);
    }
}

