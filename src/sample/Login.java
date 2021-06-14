package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Login {
    @FXML
    private Label userType;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    Passenger passenger;

    @FXML
    void register(MouseEvent event) throws IOException{

        if(userType.getText().equals("Admin")){

            Alert alert = new Alert(Alert.AlertType.NONE);

            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Sorry Registration is only for Passengers.\nPlease to back to login as user.\nOr contact us to become an admin");
            alert.show();
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    boolean loginToHome(ActionEvent event) throws IOException {
        try{
            passenger = new Passenger();
            passenger.login(userName.getText(), password.getText());
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
             stage.show();
             return true;
        }
        catch (Exception e){
            System.out.println("Login error");
            System.out.println(userName.getText()+"\n"+password.getText());
            return false;
        }
    }

    void setUserType(String type){
        userType.setText(type);
    }
}
