package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.sql.*;

public class ShowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TextField stoneWidth;

    @FXML
    private TextField stoneHight;

    @FXML
    private Button buttonNext;

    @FXML
    private ListView<?> stoneList;

    @FXML
    private Button buttonCalculate;

    @FXML
    private Text stonePrice;

    Connection co;

    ListView listView;

    int p1;

    @FXML
    void initialize() {
        try{
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:stones.db");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            Statement statement = co.createStatement();
            String query = "SELECT id, name, price FROM stones;";
            ResultSet rs = statement.executeQuery(query);
            listView = stoneList;
            while(rs.next()){
                int id = rs.getInt("id");
                String sname = rs.getString("name");
                int sprice = rs.getInt("price");
                listView.getItems().add(sname);
            }
            rs.close();
            statement.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        buttonNext.setOnAction(actionEvent -> {
            buttonNext.getScene().getWindow().hide();
        });

        buttonCalculate.setOnAction(actionEvent -> {
            try {
                Statement statement = co.createStatement();
                String query = "SELECT id, name, price FROM stones;";
                ResultSet rs = statement.executeQuery(query);
                listView = stoneList;
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String sname = rs.getString("name");
                    int sprice = rs.getInt("price");
                    if (listView.getSelectionModel().getSelectedItem().equals(sname)) {
                        int a;
                        int b;
                        int c;
                        int p;
                        a = Integer.parseInt(stoneWidth.getText());
                        b = Integer.parseInt(stoneHight.getText());
                        c = a*b;
                        p = c * sprice;
                        stonePrice.setText(Integer.toString(p));
                    }
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
