package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHome implements Initializable {
    @FXML
    Pane rightSide;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Pane view = FXMLLoader.load(getClass().getResource("adminFlights.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gotoFlights(ActionEvent event){
        try {
            Pane view = FXMLLoader.load(getClass().getResource("adminFlights.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void gotoUsers(ActionEvent event){
        try {
            Pane view = FXMLLoader.load(getClass().getResource("adminUsers.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void adduserFlight(ActionEvent event){
        try {
            Pane view = FXMLLoader.load(getClass().getResource("adminBook.fxml"));
            rightSide.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void close(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
