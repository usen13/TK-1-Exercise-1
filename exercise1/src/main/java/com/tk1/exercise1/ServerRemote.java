package com.tk1.exercise1;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRemote extends Remote {
    public Boolean login(String clientName) throws RemoteException, NotBoundException;

    public Boolean logout(String clientName) throws RemoteException;

    public Boolean updateFlight(String clientName, Flight flight) throws RemoteException;

    public Boolean deleteFlight(String clientName, Flight flight) throws RemoteException;
}
