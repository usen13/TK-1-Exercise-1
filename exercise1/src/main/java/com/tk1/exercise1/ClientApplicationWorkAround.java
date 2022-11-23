package com.tk1.exercise1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ClientApplicationWorkAround extends Application {
    private static ServerRemote server;
    private static String clientUuid;
    private static Client client;

    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        Registry registry = LocateRegistry.getRegistry(1888);

        UUID uuid = UUID.randomUUID();
        client = new Client(uuid.toString());
        registry.bind("client-" + client.getId(), client);

        server = (ServerRemote) registry.lookup("server");

        client.registerServer(server);

        clientUuid = client.getId();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<Flight> tableView = new TableView<Flight>();
        client.setTableViewFlights(tableView);
        Boolean logged = server.login(client.getId());
        if (!logged) {
            System.err.println("couldn't log in");
            return;
        }
        System.out.println("logged in!");


        TableColumn<Flight, String> airline = new TableColumn<>("Operating Airline");
        airline.setCellValueFactory(new PropertyValueFactory<>("airlineName"));

        TableColumn<Flight, String> flightNumber = new TableColumn<>("Flight Number");
        flightNumber.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));

        TableColumn<Flight, String> departureAirport = new TableColumn<>("Departure");
        departureAirport.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));

        TableColumn<Flight, String> arrivalAirport = new TableColumn<>("Arrival");
        arrivalAirport.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));

        TableColumn<Flight, String> departureTime = new TableColumn<>("Departure Time");
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureScheduledDate"));

        TableColumn<Flight, String> arrivalTime = new TableColumn<>("Arrival Time");
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalScheduledDate"));

        TableColumn<Flight, String> terminal = new TableColumn<>("Terminal");
        terminal.setCellValueFactory(new PropertyValueFactory<>("departureTerminal"));

        tableView.getColumns().addAll(airline, flightNumber, departureAirport, arrivalAirport, departureTime, arrivalTime, terminal);

        GridPane showPane = new GridPane();
        showPane.setPadding(new Insets(20, 20, 20, 20));
        showPane.setAlignment(Pos.CENTER);
        showPane.setHgap(10);
        showPane.setVgap(10);
        Button newB = new Button("New");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        showPane.add(newB, 0, 0);
        showPane.add(edit, 1, 0);
        showPane.add(delete, 2, 0);


        VBox vbox = new VBox(tableView);
        vbox.getChildren().add(showPane);
        Scene showScene = new Scene(vbox);

        // -------------------------------------------------------

        Label idLabel = new Label("ID");
        TextField idText = new TextField();
        idText.setDisable(true);

        Label iataLabel = new Label("IATA Code");
        TextField iataText = new TextField();


        // operating Airline
        Label operatingLabel = new Label("Operating Airline");
        TextField operatingText = new TextField();

        // Aircraft Model Name
        Label aircraftLabel = new Label("Aircraft Model Name");
        TextField aircraftText = new TextField();

        // Tracking Number
        Label trackingLabel = new Label("Tracking Number");
        TextField trackingText = new TextField();

        // Departure Airport
        Label departureLabel = new Label("Departure Airport");
        TextField departureText = new TextField();

        // Arrival Airport
        Label arrivalLabel = new Label("Arrival Airport");
        TextField arrivalText = new TextField();

        // Origin Date
        Label originLabel = new Label("Origin Label");
        TextField originDate = new TextField();

        // Scheduled Departure
        Label scheduledDepartureLabel = new Label("Scheduled Departure");
        TextField scheduledDepartureDate = new TextField();

        // Scheduled Arrival
        Label scheduledArrivalLabel = new Label("Scheduled Arrival");
        TextField scheduledArrivalDate = new TextField();

        // Departure Terminal
        Label departureTerminalLabel = new Label("Departure Terminal");
        TextField departureTerminalText = new TextField();

        // Arrival Terminal
        Label arrivalTerminalLabel = new Label("Arrival Terminal");
        TextField arrivalTerminalText = new TextField();

        // Departure Gates
        Label departureGatesLabel = new Label("Departure Gates");
        TextField departureGatesText = new TextField();

        // Arrival Gates
        Label arrivalGatesLabel = new Label("Arrival Gates");
        TextField arrivalGatesText = new TextField();

        // Estimated Departure
        Label estimatedDepartureLabel = new Label("Estimated Departure");
        TextField estimatedDepartureDate = new TextField();

        // Estimated Arrival
        Label estimatedArrivalLabel = new Label("Estimated Arrival");
        TextField estimatedArrivalDate = new TextField();

        // check-in location
        Label checkInLocationLabel = new Label("Check-in Location");
        TextField checkInLocationText = new TextField();

        // check-in counter
        Label checkInCounterLabel = new Label("Check-in Counter");
        TextField checkInCounterText = new TextField();

        // check-in start
        Label checkInStartLabel = new Label("Check-in Start");
        TextField checkInStartDate = new TextField();

        // check-in end
        Label checkInEndLabel = new Label("Check-in End");
        TextField checkInEndDate = new TextField();


        // flight status
        Label flightStatusLabel = new Label("Flight Status");
        String choices[] = {"B", "D", "I", "L", "M", "S", "X", "Y", "Z"};
        ChoiceBox flightStatusBox = new ChoiceBox(FXCollections.observableArrayList(choices));


        // save Button
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");


        GridPane editPane = new GridPane();
        editPane.setPadding(new Insets(20, 20, 20, 20));
        editPane.setAlignment(Pos.CENTER);
        editPane.setHgap(10);
        editPane.setVgap(10);
        Scene editScene = new Scene(editPane);

        editPane.add(iataLabel, 0, 0);
        editPane.add(iataText, 1, 0);
        editPane.add(operatingLabel, 2, 0);
        editPane.add(operatingText, 3, 0);

        editPane.add(aircraftLabel, 0, 1);
        editPane.add(aircraftText, 1, 1);
        editPane.add(idLabel, 2, 1);
        editPane.add(idText, 3, 1);

        editPane.add(trackingLabel, 0, 2);
        editPane.add(trackingText, 1, 2);

        editPane.add(departureLabel, 0, 3);
        editPane.add(departureText, 1, 3);
        editPane.add(arrivalLabel, 2, 3);
        editPane.add(arrivalText, 3, 3);

        editPane.add(originLabel, 0, 4);
        editPane.add(originDate, 1, 4);

        editPane.add(scheduledDepartureLabel, 0, 5);
        editPane.add(scheduledDepartureDate, 1, 5);
        editPane.add(scheduledArrivalLabel, 2, 5);
        editPane.add(scheduledArrivalDate, 3, 5);

        editPane.add(departureTerminalLabel, 0, 6);
        editPane.add(departureTerminalText, 1, 6);
        editPane.add(arrivalTerminalLabel, 2, 6);
        editPane.add(arrivalTerminalText, 3, 6);


        editPane.add(departureGatesLabel, 0, 7);
        editPane.add(departureGatesText, 1, 7);
        editPane.add(arrivalGatesLabel, 2, 7);
        editPane.add(arrivalGatesText, 3, 7);

        editPane.add(estimatedDepartureLabel, 0, 8);
        editPane.add(estimatedDepartureDate, 1, 8);
        editPane.add(estimatedArrivalLabel, 2, 8);
        editPane.add(estimatedArrivalDate, 3, 8);

        editPane.add(checkInLocationLabel, 0, 9);
        editPane.add(checkInLocationText, 1, 9);

        editPane.add(checkInCounterLabel, 0, 10);
        editPane.add(checkInCounterText, 1, 10);

        editPane.add(checkInStartLabel, 0, 11);
        editPane.add(checkInStartDate, 1, 11);
        editPane.add(checkInEndLabel, 2, 11);
        editPane.add(checkInEndDate, 3, 11);

        editPane.add(flightStatusLabel, 0, 12);
        editPane.add(flightStatusBox, 1, 12, 3, 1);
        flightStatusBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        editPane.add(cancelButton, 2, 14);
        editPane.add(saveButton, 3, 14);
        saveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cancelButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // ------------------------------------------------------------------
        newB.setOnAction(event -> {
            idText.setText(UUID.randomUUID().toString());
            iataText.setText("");
            operatingText.setText("");
            aircraftText.setText("");
            trackingText.setText("");
            departureText.setText("");
            arrivalText.setText("");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            originDate.setText(dateFormat.format(new Date()));
            scheduledDepartureDate.setText(dateFormat.format(new Date()));
            scheduledArrivalDate.setText(dateFormat.format(new Date()));
            departureTerminalText.setText("");
            arrivalTerminalText.setText("");
            departureGatesText.setText("");
            arrivalGatesText.setText("");
            estimatedDepartureDate.setText(dateFormat.format(new Date()));
            estimatedArrivalDate.setText(dateFormat.format(new Date()));
            checkInLocationText.setText("");
            checkInCounterText.setText("");
            checkInStartDate.setText(dateFormat.format(new Date()));
            checkInEndDate.setText(dateFormat.format(new Date()));
            flightStatusBox.setValue(Flight.Status.B);
            Stage stage = new Stage();
            stage.setTitle("Flight");
            stage.setScene(editScene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        });

        edit.setOnAction(event -> {
            Flight f = tableView.getSelectionModel().getSelectedItem();
            if (f == null) {
                return;
            }
            idText.setText(f.getId());
            iataText.setText(f.getIataCode());
            operatingText.setText(f.getAirlineName());
            aircraftText.setText(f.getAircraftName());
            trackingText.setText(f.getFlightNumber());
            departureText.setText(f.getDepartureAirport());
            arrivalText.setText(f.getArrivalAirport());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            originDate.setText(dateFormat.format(f.getOriginDate()));
            scheduledDepartureDate.setText(dateFormat.format(f.getDepartureScheduledDate()));
            scheduledArrivalDate.setText(dateFormat.format(f.getArrivalScheduledDate()));
            departureTerminalText.setText(f.getDepartureTerminal());
            arrivalTerminalText.setText(f.getArrivalTerminal());
            departureGatesText.setText(f.getDepartureGates());
            arrivalGatesText.setText(f.getArrivalGates());
            estimatedDepartureDate.setText(dateFormat.format(f.getDepartureEstimatedDate()));
            estimatedArrivalDate.setText(dateFormat.format(f.getArrivalEstimatedDate()));
            checkInLocationText.setText(f.getCheckInLocation());
            checkInCounterText.setText(f.getCheckInCounter());
            checkInStartDate.setText(dateFormat.format(f.getCheckInStartDate()));
            checkInEndDate.setText(dateFormat.format(f.getCheckInEndDate()));
            flightStatusBox.setValue(f.getStatus());

            Stage stage = new Stage();
            stage.setTitle("Flight " + f.getFlightNumber());
            stage.setScene(editScene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        });

        delete.setOnAction(event -> {
            Flight f = tableView.getSelectionModel().getSelectedItem();
            if (f == null) {
                return;
            }

            try {
                server.deleteFlight(clientUuid, f);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        saveButton.setOnAction(event -> {
            Flight f = new Flight();
            f.setId(idText.getText());
            f.setIataCode(iataText.getText());
            f.setAirlineName(operatingText.getText());
            f.setAircraftName(aircraftText.getText());
            f.setFlightNumber(trackingText.getText());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            f.setDepartureAirport(departureText.getText());
            f.setArrivalAirport(arrivalText.getText());
            f.setDepartureTerminal(departureTerminalText.getText());
            f.setArrivalTerminal(arrivalTerminalText.getText());
            f.setDepartureGates(departureGatesText.getText());
            f.setArrivalGates(arrivalGatesText.getText());
            f.setCheckInLocation(checkInLocationText.getText());
            f.setCheckInCounter(checkInCounterText.getText());
            switch (flightStatusBox.getValue().toString()) {
                case "B" -> f.setStatus(Flight.Status.B);
                case "D" -> f.setStatus(Flight.Status.D);
                case "I" -> f.setStatus(Flight.Status.I);
                case "L" -> f.setStatus(Flight.Status.L);
                case "M" -> f.setStatus(Flight.Status.M);
                case "S" -> f.setStatus(Flight.Status.S);
                case "X" -> f.setStatus(Flight.Status.X);
                case "Y" -> f.setStatus(Flight.Status.Y);
                case "Z" -> f.setStatus(Flight.Status.Z);
            }
            try {
                f.setOriginDate(dateFormat.parse(originDate.getText()));
                f.setDepartureScheduledDate(dateFormat.parse(scheduledDepartureDate.getText()));
                f.setArrivalScheduledDate(dateFormat.parse(scheduledArrivalDate.getText()));
                f.setDepartureEstimatedDate(dateFormat.parse(estimatedDepartureDate.getText()));
                f.setArrivalEstimatedDate(dateFormat.parse(estimatedArrivalDate.getText()));
                f.setCheckInStartDate(dateFormat.parse(checkInStartDate.getText()));
                f.setCheckInEndDate(dateFormat.parse(checkInEndDate.getText()));
            } catch (ParseException e) {
                System.err.println("Date is malformatted");
                return;
            }
            try {
                server.updateFlight(clientUuid, f);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setTitle("Flights list");
            stage.setScene(showScene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        });

        cancelButton.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Flights list");
            stage.setScene(showScene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        });

        primaryStage.setTitle("Flights list");
        primaryStage.setScene(showScene);
        primaryStage.show();
    }
}
