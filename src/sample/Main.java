package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        root = FXMLLoader.load(getClass().getResource("welcome.fxml"));

        primaryStage.setTitle("Maestro Airlines");
        button = new Button();
        button.setText("Start");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        String css = this.getClass().getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
