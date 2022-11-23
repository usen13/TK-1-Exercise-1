package com.tk1.exercise1;

import javafx.scene.control.TableView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Client extends UnicastRemoteObject implements ClientRemote {
    private List<Flight> flights;
    private ServerRemote server;
    private String id;

    private TableView<Flight> tableViewFlights;

    public Client(String id) throws RemoteException {
        super();
        this.flights = new ArrayList<>();
        this.id = id;
    }

    @Override
    public void sendFlightsUpdate(List<Flight> flights) throws RemoteException {
        this.flights = flights;

        tableViewFlights.getItems().remove(0, this.tableViewFlights.getItems().size());

        for (Flight flight : flights) {
            tableViewFlights.getItems().add(flight);
        }
    }

    @Override
    public void registerServer(ServerRemote server) throws RemoteException {
        this.server = server;
    }

    public ServerRemote getServer() {
        return server;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public String getId() {
        return id;
    }

    public TableView<Flight> getTableViewFlights() {
        return tableViewFlights;
    }

    public void setTableViewFlights(TableView<Flight> tableViewFlights) {
        this.tableViewFlights = tableViewFlights;
    }

}
