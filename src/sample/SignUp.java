package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    Passenger passenger;

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
    TextField gender;

    @FXML
    TextField address;


    @FXML
    void registerUser(ActionEvent event) {
        passenger = new Passenger();
        String fn = firstname.getText();
        String ln = lastname.getText();
        String un = username.getText();
        String pw = password.getText();
        String ppNo = passportNo.getText();
        String pheNo = phoneNo.getText();
        String add = address.getText();
        String gen = gender.getText();

       boolean test =  passenger.createAccount(fn,ln,pw,ppNo,pheNo, gen, add, un);
        try{
            if(test){
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e){

        }


    }

    @FXML
    void gotoLogin(MouseEvent event){
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
