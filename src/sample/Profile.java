package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Profile implements Initializable {
    @FXML
    Label no_of_flights;

    @FXML
    Label profile_name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        no_of_flights.setText("7");
        profile_name.setText("Maestro");

    }
}
