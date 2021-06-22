package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class Controller {
    FXMLLoader loader;
    Parent root;
    Stage stage;
    Scene scene;
    public static Passenger passenger1;

    @FXML
    Pane rightSide;

    @FXML
    private void initialize() {
        try {
            Pane view;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("flights.fxml"));
            view = loader.load();
            UserFlightController details = loader.getController();
            details.getInfo(passenger1);
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void gotoFlights(ActionEvent action) throws IOException {
        try {
            Pane view;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("flights.fxml"));
            view = loader.load();
            rightSide.getChildren().setAll(view);
            UserFlightController details = loader.getController();
            details.getInfo(passenger1);
            System.out.println(passenger1.username);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void gotoProfile(ActionEvent action){
        try {
            Pane view;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
            view = loader.load();
            rightSide.getChildren().setAll(view);
            Profile details = loader.getController();
            details.getUser(passenger1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void logout(){}


    void setInfo(Passenger passenger){
        passenger1 = passenger;
    }

    @FXML
    void logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
