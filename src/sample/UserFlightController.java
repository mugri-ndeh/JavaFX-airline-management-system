package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class UserFlightController implements Initializable {

    Passenger person;

    @FXML
    TextField flight_id;
    @FXML
    TextField num_of_seats;


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

    @FXML
    void bookFlight(ActionEvent e){
        System.out.println(person.username);
        System.out.println(num_of_seats.getText());
        try{
            conn = new Driver().getConnection();
            String sql = "INSERT INTO booked (username, flight_id, num_of_seats) VALUES (?,?,?)";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, person.username);
            query.setString(2, flight_id.getText());
            query.setInt(3, Integer.parseInt(num_of_seats.getText()));
            query.executeUpdate();
            conn.close();
            System.out.println("Successfully booked");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showFlights();
        //System.out.println(person.username);
    }


    void getInfo(Passenger passenger){
        person = passenger;
    }
}
