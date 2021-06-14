package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @FXML
    Pane rightSide;

    @FXML
    private void initialize() {
        try {
            Pane view = FXMLLoader.load(getClass().getResource("flights.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gotoBooked(ActionEvent action) throws IOException {
        try {
            Pane view = FXMLLoader.load(getClass().getResource("booked.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gotoFlights(ActionEvent action) throws IOException {
        try {
            Pane view = FXMLLoader.load(getClass().getResource("flights.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gotoHistory(ActionEvent action){
        try {
            Pane view = FXMLLoader.load(getClass().getResource("history.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gotoProfile(ActionEvent action){
        try {
            Pane view = FXMLLoader.load(getClass().getResource("profile.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void logout(){}
}
