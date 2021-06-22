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
import java.util.ResourceBundle;

public class AdminUsers implements Initializable {
    @FXML
    TextField first_name;
    @FXML
    TextField last_name;
    @FXML
    TextField username;
    @FXML
    PasswordField pass_word;
    @FXML
    TextField passport_num;
    @FXML
    TextField phone_num;
    @FXML
    TextField address;
    @FXML
    ComboBox<String> gender_sel;

    @FXML
    TableColumn<Passenger,String> tbl_first_name;
    @FXML
    TableColumn<Passenger,String> tbl_last_name;
    @FXML
    TableColumn<Passenger,String> tbl_username;
    @FXML
    TableColumn<Passenger,String> tbl_passport_num;
    @FXML
    TableColumn<Passenger,Integer> tbl_phone_num;
    @FXML
    TableColumn<Passenger,String> tbl_address;
    @FXML
    TableColumn<Passenger,String> tbl_gender;
    @FXML
    TableView<Passenger> user_tbl;

    ObservableList<String> options = FXCollections.observableArrayList("Male","Female");


    Connection conn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender_sel.getItems().addAll(options);
        System.out.println(gender_sel.getSelectionModel().getSelectedItem());
        showPassengers();
    }

    @FXML
    void reset(){
        try{
            first_name.setText(user_tbl.getSelectionModel().getSelectedItem().getFirst_name());
            last_name.setText(user_tbl.getSelectionModel().getSelectedItem().getLast_name());
            username.setText(user_tbl.getSelectionModel().getSelectedItem().getUsername());
            pass_word.setText(user_tbl.getSelectionModel().getSelectedItem().getPass_word());
            passport_num.setText(user_tbl.getSelectionModel().getSelectedItem().getPassport_num());
            phone_num.setText(String.valueOf(user_tbl.getSelectionModel().getSelectedItem().getPhone_num()));
            address.setText(user_tbl.getSelectionModel().getSelectedItem().getAddress());
        }catch (Exception e){

        }
    }

    @FXML
    void clear(ActionEvent event){
        first_name.setText("");
        last_name.setText("");
        username.setText("");
        pass_word.setText("");
        passport_num.setText("");
        phone_num.setText("");
        address.setText("");
    }


    @FXML
    void addUser(ActionEvent event){
        try{
            conn = new Driver().getConnection();
            String sql = "INSERT INTO user_tbl (first_name, last_name, username, pass_word, passport_num, phone_num, gender, address) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, first_name.getText());
            query.setString(2, last_name.getText());
            query.setString(3, username.getText());
            query.setString(4, pass_word.getText());
            query.setString(5, passport_num.getText());
            query.setInt(6, Integer.parseInt(phone_num.getText()));
            query.setString(7, gender_sel.getSelectionModel().getSelectedItem());
            query.setString(8, address.getText());

            query.executeUpdate();
            conn.close();
            alert("User added");
            System.out.println("Successful addition user_tbl");
            showPassengers();

        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void deleteUser(ActionEvent event){
        try{
            conn = new Driver().getConnection();
            String sql = "DELETE FROM user_tbl WHERE passport_num = ?";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1,user_tbl.getSelectionModel().getSelectedItem().getPassport_num());

            query.executeUpdate();
            conn.close();
            alert("User deleted");
            showPassengers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void updateUser(ActionEvent event){
        String sql = "UPDATE user_tbl SET first_name = ?, last_name = ?, username = ?, pass_word = ?, passport_num = ?, phone_num = ?, gender = ?, address = ? WHERE  passport_num = ?";
        try{
            conn = new Driver().getConnection();
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, first_name.getText());
            query.setString(2, last_name.getText());
            query.setString(3, username.getText());
            query.setString(4, pass_word.getText());
            query.setString(5, passport_num.getText());
            query.setInt(6, Integer.parseInt(phone_num.getText()));
            query.setString(7, gender_sel.getSelectionModel().getSelectedItem());
            query.setString(8, address.getText());
            query.setString(9, user_tbl.getSelectionModel().getSelectedItem().getPassport_num());
            query.executeUpdate();

            alert("User updated");
            showPassengers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void drop(MouseEvent event){
        System.out.println(gender_sel.getSelectionModel().getSelectedItem());
    }

    public ObservableList<Passenger> getPassenger(){
        ObservableList<Passenger> passengers = FXCollections.observableArrayList();
        conn = new Driver().getConnection();
        String sql = "SELECT * FROM user_tbl";
        Statement query;
        ResultSet result;
        try{
            query = conn.createStatement();
            result = query.executeQuery(sql);
            Passenger passenger;
            while (result.next()){
                passenger = new Passenger(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("username"),
                        result.getString("pass_word"),
                        result.getString("passport_num"),
                        result.getInt("phone_num"),
                        result.getString("gender"),
                        result.getString("address"),
                        result.getString("flight_id")
                );
                passengers.add(passenger);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return passengers;
    }
    void showPassengers(){
        ObservableList<Passenger> passengers = getPassenger();
        tbl_first_name.setCellValueFactory(new PropertyValueFactory<Passenger, String>("first_name"));
        tbl_last_name.setCellValueFactory(new PropertyValueFactory<Passenger, String>("last_name"));
        tbl_username.setCellValueFactory(new PropertyValueFactory<Passenger, String>("username"));
        tbl_passport_num.setCellValueFactory(new PropertyValueFactory<Passenger, String>("passport_num"));
        tbl_phone_num.setCellValueFactory(new PropertyValueFactory<Passenger, Integer>("phone_num"));
        tbl_gender.setCellValueFactory(new PropertyValueFactory<Passenger, String>("gender"));
        tbl_address.setCellValueFactory(new PropertyValueFactory<Passenger, String>("address"));
        user_tbl.setItems(passengers);
    }

    void alert(String message){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
