package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Flight;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminFlights implements Initializable {
    @FXML
    TextField flightID;
    @FXML
    TextField source;
    @FXML
    TextField destination;
    @FXML
    DatePicker date;
    @FXML
    TextField arrivalTime;
    @FXML
    TextField cost;
    @FXML
    TextField deptTime;
    @FXML
    TextField noOfSeats;

    @FXML
    TableView<Flight> flight_tbl;
    @FXML
    TableColumn<Flight, String> tbl_flight_id;
    @FXML
    TableColumn<Flight, String> tbl_source;
    @FXML
    TableColumn<Flight, String> tbl_destination;
    @FXML
    TableColumn<Flight, String> tbl_date;
    @FXML
    TableColumn<Flight, String> tbl_departure_time;
    @FXML
    TableColumn<Flight, String> tbl_arrival_time;
    @FXML
    TableColumn<Flight, String> tbl_num_of_seats;
    @FXML
    TableColumn<Flight, String> tbl_cost;


    Connection conn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showFlights();
    }

    @FXML
    void getItem(MouseEvent event) {
        try{
            flightID.setText(flight_tbl.getSelectionModel().getSelectedItem().getFlight_id());
            source.setText(flight_tbl.getSelectionModel().getSelectedItem().getSource());
            destination.setText(flight_tbl.getSelectionModel().getSelectedItem().getDestination());
            deptTime.setText(flight_tbl.getSelectionModel().getSelectedItem().getDeparture_time());
            arrivalTime.setText(flight_tbl.getSelectionModel().getSelectedItem().getArrival_time());
            cost.setText(String.valueOf(flight_tbl.getSelectionModel().getSelectedItem().getCost()));
            noOfSeats.setText(String.valueOf(flight_tbl.getSelectionModel().getSelectedItem().getNum_of_seats()));
        }catch (Exception e){
        }

    }

    @FXML
    void clear(ActionEvent event){
        flightID.setText("");
        source.setText("");
        destination.setText("");
        deptTime.setText("");
        arrivalTime.setText("");
        cost.setText("");
        noOfSeats.setText("");
    }

    @FXML
    void addFlight(ActionEvent event) throws Exception {
        try{
            conn = new Driver().getConnection();
            String sql = "INSERT INTO flight (flight_id, source, destination, _date,departure_time, arrival_time,num_of_seats, cost) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, flightID.getText());
            query.setString(2, source.getText());
            query.setString(3, destination.getText());
            query.setDate(4, Date.valueOf(date.getValue()));
            query.setString(5, deptTime.getText());
            query.setString(6, arrivalTime.getText());
            query.setInt(7, Integer.parseInt(noOfSeats.getText()));
            query.setInt(8, Integer.parseInt(cost.getText()));

            query.executeUpdate();
            conn.close();
            alert("Successfully added");
            showFlights();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void updateFlight(ActionEvent event){
        String sql = "UPDATE flight SET flight_id = ?, source = ?, destination = ?, _date = ?, departure_time = ?, arrival_time = ?, num_of_seats = ?, cost = ? WHERE  flight_id = ?";
        try{
            conn = new Driver().getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, flightID.getText());
            query.setString(2, source.getText());
            query.setString(3, destination.getText());
            query.setDate(4, Date.valueOf(date.getValue()));
            query.setString(5, deptTime.getText());
            query.setString(6, arrivalTime.getText());
            query.setInt(7, Integer.parseInt(noOfSeats.getText()));
            query.setInt(8, Integer.parseInt(cost.getText()));
            query.setString(9, flight_tbl.getSelectionModel().getSelectedItem().getFlight_id());
            query.executeUpdate();

            alert("Successfully updated");
            showFlights();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteFlight(ActionEvent event){
        try{
            conn = new Driver().getConnection();
            String sql = "DELETE FROM flight WHERE flight_id = ?";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,flight_tbl.getSelectionModel().getSelectedItem().getFlight_id());

            query.executeUpdate();
            conn.close();
            alert("Successfully deleted");
            showFlights();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public ObservableList<Flight> getFlights(){
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        conn = new Driver().getConnection();
        String sql = "SELECT * FROM flight";
        Statement query;
        ResultSet result;
        try{
            query = conn.createStatement();
            result = query.executeQuery(sql);
            Flight flight;
            while (result.next()){
                flight = new Flight(
                        result.getString("flight_id"),
                        result.getString("source"),
                        result.getString("destination"),
                        result.getDate("_date"),
                        result.getString("departure_time"),
                        result.getString("arrival_time"),
                        result.getInt("num_of_seats"),
                        result.getInt("cost")
                );
                flights.add(flight);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flights;
    }
    void showFlights(){
        ObservableList<Flight> flights = getFlights();
        tbl_flight_id.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight_id"));
        tbl_source.setCellValueFactory(new PropertyValueFactory<Flight, String>("source"));
        tbl_destination.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        tbl_date.setCellValueFactory(new PropertyValueFactory<Flight, String>("_date"));
        tbl_departure_time.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure_time"));
        tbl_arrival_time.setCellValueFactory(new PropertyValueFactory<Flight, String>("arrival_time"));
        tbl_num_of_seats.setCellValueFactory(new PropertyValueFactory<Flight, String>("num_of_seats"));
        tbl_cost.setCellValueFactory(new PropertyValueFactory<Flight, String>("cost"));
        flight_tbl.setItems(flights);


    }

    void alert(String message){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
