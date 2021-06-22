package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    Connection conn;

    @FXML
    TextField firstname;

    @FXML
    TextField lastname;

    @FXML
    TextField username;

    @FXML
    TextField password;

    @FXML
    TextField passportNo;

    @FXML
    TextField phoneNo;

    @FXML
    ComboBox<String> gender;

    @FXML
    TextField address;

    ObservableList<String> options = FXCollections.observableArrayList("Male","Female");
    GenFunctions gen;

    @FXML
    void registerUser(ActionEvent event) {

        System.out.println(username.getText());

        String sql = "INSERT INTO user_tbl (first_name, last_name, username, pass_word, passport_num, phone_num, gender, address) VALUES (?,?,?,?,?,?,?,?) ";
        if (usernameExists(username.getText())){
            System.out.println("Username exists");
        }
         if(firstname.getText().isEmpty() ||
                 lastname.getText().isEmpty() ||
                 username.getText().isEmpty() ||
                 password.getText().isEmpty() ||
                 passportNo.getText().isEmpty() ||
                 phoneNo.getText().isEmpty() ||
                 gender.getSelectionModel().getSelectedItem().isEmpty() || address.getText().isEmpty()
         ){
             alert("One or more fields are empty!");
            System.out.println("One or more fields are empty!");
        }
        else if(password.getText().length()<5){
            alert("Weak password, it should be at least 5 characters long");
            System.out.println("Weak password, it should be at least 5 characters long");
        }
        else {
            try {
                conn = new Driver().getConnection();
                PreparedStatement query = conn.prepareStatement(sql);
                query.setString(1, firstname.getText());
                query.setString(2, lastname.getText());
                query.setString(3, username.getText());
                query.setString(4, password.getText());
                query.setString(5, passportNo.getText());
                query.setInt(6, Integer.parseInt(phoneNo.getText()));
                query.setString(7, gender.getSelectionModel().getSelectedItem());
                query.setString(8, address.getText());

                query.executeUpdate();
                conn.close();
                alert("Successfully added");
                Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (SQLException | IOException throwables) {
                System.out.println(throwables.getMessage());
                alert("Username Exists\nChoose another");
                System.out.println("Username exists");
            }

        }

    }

    @FXML
    void gotoLogin(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(options);
    }

    boolean usernameExists(String username) {
        try {
            conn = new Driver().getConnection();
            String sql = "SELECT * FROM user_tbl WHERE username = ?";
            PreparedStatement query = conn.prepareStatement(sql);
            query.setString(1, username);
            ResultSet rs = query.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    void alert(String message){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }
}
