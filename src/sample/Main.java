package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Stone Pricer 1.0");
        primaryStage.setScene(new Scene(root, 694, 449));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
