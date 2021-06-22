package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private Label userType;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    Passenger passenger;
    Connection conn;

    @FXML
    void gotoWelcome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
    void loginToHome(ActionEvent event) throws IOException {

        if(userType.getText().equals("Admin")){

            if(userName.getText().equals(Admin.name) && password.getText().equals(Admin.password)){
                Parent root = FXMLLoader.load(getClass().getResource("adminHome.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("User Not found\nCheck credentials");
                alert.show();
            }

        }
        else{
            try{
                passenger = new Passenger();
                conn = new Driver().getConnection();
                String sql = "SELECT * FROM user_tbl WHERE username = ? AND pass_word = ?";
                PreparedStatement query = conn.prepareStatement(sql);
                query.setString(1, userName.getText());
                query.setString(2,password.getText());
                ResultSet resultSet = query.executeQuery();
                if (resultSet.next()){
                    System.out.println("User found");
                     Passenger passenger = new Passenger(userName.getText(), password.getText());
                    Parent root;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
                    root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    Controller details = loader.getController();
                    details.setInfo(passenger);
                    stage.setScene(scene);
                    stage.show();

                }
                else {
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("User Not found\nCheck credentials");
                    alert.show();
                }
            }
            catch (Exception e){
                System.out.println("Login error");
                System.out.println(userName.getText()+"\n"+password.getText());

            }
        }

    }


    void setUserType(String type){
        userType.setText(type);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(userType.getText());
    }
}
