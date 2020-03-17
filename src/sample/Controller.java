package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField stoneName;

    @FXML
    private TextField stonePrice;

    @FXML
    private Button buttonAddStone;

    @FXML
    private Button buttonSkip;

    Connection co;

    @FXML
    void initialize() {
        buttonSkip.setOnAction(actionEvent -> {
            buttonSkip.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/showSample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });


        try{
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:stones.db");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        buttonAddStone.setOnAction(actionEvent -> {
            String sname = stoneName.getText();
            int sprice = Integer.parseInt(stonePrice.getText());
            String query = "INSERT INTO stones (name,price) "+"VALUES ('"+sname+"','"+sprice+"')";
            try {
                Statement statement = co.createStatement();
                statement.executeUpdate(query);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
                co.close();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
    }
}
