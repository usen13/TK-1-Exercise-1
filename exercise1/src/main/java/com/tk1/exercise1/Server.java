package com.tk1.exercise1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server extends UnicastRemoteObject implements ServerRemote {
    private HashMap<String, ClientRemote> clients;
    private List<Flight> flights;

    public Server() throws RemoteException {
        super();
        Flight flight = new Flight();
        flight.setId(UUID.randomUUID().toString());
        flight.setIataCode("LH");
        flight.setAirlineName("Lufthansa");
        flight.setAircraftName("A380");
        flight.setFlightNumber("591");
        flight.setDepartureAirport("NBO");
        flight.setArrivalAirport("FRA");
        flight.setOriginDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 11).getTime());
        flight.setArrivalScheduledDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 22).getTime());
        flight.setArrivalTerminal("T1");
        flight.setArrivalGates("C15A");
        flight.setArrivalEstimatedDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 22).getTime());
        flight.setDepartureEstimatedDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 22).getTime());
        flight.setDepartureScheduledDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 11).getTime());
        flight.setDepartureTerminal("T5");
        flight.setDepartureGates("B54");
        flight.setCheckInLocation("1");
        flight.setCheckInCounter("664");
        flight.setCheckInStartDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 12).getTime());
        flight.setCheckInEndDate(new GregorianCalendar(2022, Calendar.NOVEMBER, 12).getTime());
        flight.setStatus(Flight.Status.S);
        this.flights = new ArrayList<>();
        this.flights.add(flight);
        this.clients = new HashMap<>();
    }

    @Override
    public Boolean login(String clientName) throws RemoteException, NotBoundException {
        ClientRemote c = clients.get(clientName);
        if (c != null) {
            return true;
        }
        Registry registry = LocateRegistry.getRegistry(1888);
        ClientRemote client = (ClientRemote) registry.lookup("client-" + clientName);
        clients.put(clientName, client);
        // send update to all clients
        for (ClientRemote cr : this.clients.values()) {
            cr.sendFlightsUpdate(this.flights);
        }
        return true;
    }

    @Override
    public Boolean logout(String clientName) throws RemoteException {
        ClientRemote c = clients.get(clientName);
        if (c != null) {
            this.clients.remove(clientName);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateFlight(String clientName, Flight flight) throws RemoteException {
        ClientRemote c = clients.get(clientName);
        if (c == null) {
            throw new RemoteException("you must be logged in");
        }
        int index = -1;
        for (int i = 0; i < this.flights.size(); i++) {
            if (this.flights.get(i).getId().equals(flight.getId())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            this.flights.add(flight);
        } else {
            this.flights.set(index, flight);
        }
        // send update to all the clients
        for (ClientRemote client : this.clients.values()) {
            client.sendFlightsUpdate(this.flights);
        }
        return true;
    }

    @Override
    public Boolean deleteFlight(String clientName, Flight flight) throws RemoteException {
        ClientRemote c = clients.get(clientName);
        if (c == null) {
            throw new RemoteException("you must be logged in");
        }
        int index = -1;
        for (int i = 0; i < this.flights.size(); i++) {
            if (this.flights.get(i).getId().equals(flight.getId())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        this.flights.remove(index);
        for (ClientRemote client : this.clients.values()) {
            client.sendFlightsUpdate(this.flights);
        }
        return true;
    }

}
