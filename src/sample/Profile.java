package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample.Controller.passenger1;

public class Profile implements Initializable {
    @FXML
    TextField username;
    @FXML
    ComboBox<String> flight_id;
    @FXML
    TextField num_of_seats;
    Connection conn;

    @FXML
    TableView<Booked> flight_tbl;
    @FXML
    TableColumn<Booked, String> tbl_flight_id;

    @FXML
    TableColumn<Booked, Integer> tbl_num_of_seats;
    @FXML
    TableColumn<Booked, Integer> tbl_cost;
    @FXML
    TableColumn<Booked, String> tbl_username;

    Passenger user;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flight_id.setItems(FXCollections.observableArrayList(setFlightID()));
        showBooked();
    }

    @FXML
    String getItem(MouseEvent event) {
        String id = flight_tbl.getSelectionModel().getSelectedItem().getFlight_id();
        System.out.println(id);
        reset();
        return id;
    }

    @FXML
    void reset(){
        username.setText(flight_tbl.getSelectionModel().getSelectedItem().getUsername());
        num_of_seats.setText(String.valueOf(flight_tbl.getSelectionModel().getSelectedItem().getNum_of_seats()));
    }

    @FXML
    void clear(ActionEvent event){
        username.setText("");
        num_of_seats.setText("");
    }

    @FXML
    void addUserFlight(ActionEvent event){
        String sql = "UPDATE user_tbl SET flight_id = ? WHERE username = ?";
        try{
            conn = new Driver().getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, flight_id.getSelectionModel().getSelectedItem());
            query.setString(2, username.getText());
            query.executeUpdate();
            bookFlight();
            query.close();
            conn.close();

            showBooked();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void deleteFlightt(ActionEvent event) {
        try{
            conn = new Driver().getConnection();
            String sql = "DELETE FROM booked WHERE flight_id = ?";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,flight_tbl.getSelectionModel().getSelectedItem().getFlight_id());
            query.executeUpdate();
            conn.close();
            update_user_tbl();
            showBooked();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void updateFlight(ActionEvent event) {
        String sql = "UPDATE booked SET username = ?, flight_id = ?, num_of_seats = ? WHERE username = ?";
        try{
            conn = new Driver().getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, username.getText());
            query.setString(2, flight_id.getSelectionModel().getSelectedItem());
            query.setInt(3, Integer.parseInt(num_of_seats.getText()));
            query.setString(4, flight_tbl.getSelectionModel().getSelectedItem().getUsername());
            query.executeUpdate();

            showBooked();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(flight_tbl.getSelectionModel().getSelectedItem().getUsername());
    }


    void update_user_tbl(){
        String sql = "UPDATE user_tbl SET flight_id = ? WHERE username = ?";
        try{
            conn = new Driver().getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, null);
            query.setString(2, username.getText());
            query.executeUpdate();
            query.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //populate combobox
    List<String> setFlightID(){
        List<String> options = new ArrayList<>();
        String sql = "SELECT flight_id FROM flight";
        try{
            conn = new Driver().getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            ResultSet result = query.executeQuery();
            while (result.next()){
                System.out.println(result.getString("flight_id"));
                options.add(result.getString("flight_id"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    public ObservableList<Booked> getBooked(){
        ObservableList<Booked> bookeds = FXCollections.observableArrayList();
        conn = new Driver().getConnection();
        String sql = "SELECT * FROM booked WHERE username = ?";

        try{
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,passenger1.getUsername());
            ResultSet result;
            result = query.executeQuery();
            Booked booked;
            while (result.next()){
                booked = new Booked(
                        result.getString("username"),
                        result.getString("flight_id"),
                        result.getInt("num_of_seats")
                );
                bookeds.add(booked);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bookeds;
    }
    void showBooked(){
        ObservableList<Booked> booked = getBooked();
        tbl_flight_id.setCellValueFactory(new PropertyValueFactory<Booked, String>("flight_id"));
        tbl_num_of_seats.setCellValueFactory(new PropertyValueFactory<Booked, Integer>("num_of_seats"));
        tbl_username.setCellValueFactory(new PropertyValueFactory<Booked, String>("username"));

        flight_tbl.setItems(booked);
    }

    void bookFlight(){
        if(!username.getText().equals(passenger1.getUsername())){
            System.out.println("Please input your username");
        }
        else {
            try {
                conn = new Driver().getConnection();
                String sql = "INSERT INTO booked (username, flight_id, num_of_seats) VALUES (?,?,?)";
                PreparedStatement query = conn.prepareStatement(sql);
                query.setString(1, username.getText());
                query.setString(2, flight_id.getSelectionModel().getSelectedItem());
                query.setInt(3, Integer.parseInt(num_of_seats.getText()));
                query.executeUpdate();
                conn.close();
                showBooked();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void getUser(Passenger passenger){
        user = passenger;
    }
}







