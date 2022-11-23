package com.tk1.exercise1;

import java.io.Serializable;
import java.util.Date;

public class Flight implements Serializable {

    private String id;
    private String iataCode;
    private String airlineName;
    private String aircraftName;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private Date originDate;
    private Date arrivalScheduledDate;
    private String arrivalTerminal;
    private String arrivalGates;
    private Date arrivalEstimatedDate;
    private Date departureEstimatedDate;
    private Date departureScheduledDate;
    private String departureTerminal;
    private String departureGates;
    private String checkInLocation;
    private String checkInCounter;
    private Date checkInStartDate;
    private Date checkInEndDate;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getOriginDate() {
        return originDate;
    }

    public void setOriginDate(Date originDate) {
        this.originDate = originDate;
    }

    public String getArrivalGates() {
        return arrivalGates;
    }

    public void setArrivalGates(String arrivalGates) {
        this.arrivalGates = arrivalGates;
    }

    public Date getArrivalEstimatedDate() {
        return arrivalEstimatedDate;
    }

    public void setArrivalEstimatedDate(Date arrivalEstimatedDate) {
        this.arrivalEstimatedDate = arrivalEstimatedDate;
    }

    public Date getDepartureScheduledDate() {
        return departureScheduledDate;
    }

    public void setDepartureScheduledDate(Date departureScheduledDate) {
        this.departureScheduledDate = departureScheduledDate;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getDepartureGates() {
        return departureGates;
    }

    public void setDepartureGates(String departureGates) {
        this.departureGates = departureGates;
    }

    public String getCheckInLocation() {
        return checkInLocation;
    }

    public void setCheckInLocation(String checkInLocation) {
        this.checkInLocation = checkInLocation;
    }

    public String getCheckInCounter() {
        return checkInCounter;
    }

    public void setCheckInCounter(String checkInCounter) {
        this.checkInCounter = checkInCounter;
    }

    public Date getCheckInStartDate() {
        return checkInStartDate;
    }

    public void setCheckInStartDate(Date checkInStartDate) {
        this.checkInStartDate = checkInStartDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getArrivalScheduledDate() {
        return arrivalScheduledDate;
    }

    public void setArrivalScheduledDate(Date arrivalScheduledDate) {
        this.arrivalScheduledDate = arrivalScheduledDate;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public Date getDepartureEstimatedDate() {
        return departureEstimatedDate;
    }

    public void setDepartureEstimatedDate(Date departureEstimatedDate) {
        this.departureEstimatedDate = departureEstimatedDate;
    }

    public Date getCheckInEndDate() {
        return checkInEndDate;
    }

    public void setCheckInEndDate(Date checkInEndDate) {
        this.checkInEndDate = checkInEndDate;
    }
    public enum Status {
        B, D, I, L, M, S, X, Y, Z
    }
}
