package com.tk1.exercise1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientRemote extends Remote {
    public void sendFlightsUpdate(List<Flight> flights) throws RemoteException;

    public void registerServer(ServerRemote server) throws RemoteException;
}
