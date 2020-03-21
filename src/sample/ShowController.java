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

    @FXML
    private TextField stoneHowMany;

    @FXML
    private Text obkantPrice;

    @FXML
    private Text torcPrice;

    @FXML
    private ListView<?> torcList;

    Connection co;

    Connection co2;

    ListView listView;

    ListView listView2;

    int p1;

    int price;

    int finaleobkant;

    @FXML
    void initialize() {
        try{
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:stones.db");
            co2 = DriverManager.getConnection("jdbc:sqlite:torces.db");
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
                String sname = rs.getString("name");
                listView.getItems().add(sname);
            }
            rs.close();
            statement.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            Statement statement2 = co2.createStatement();
            String query2 = "SELECT id, name, price FROM torces;";
            ResultSet rs2 = statement2.executeQuery(query2);
            listView2 = torcList;
            while(rs2.next()){
                String tname = rs2.getString("name");
                listView2.getItems().add(tname);
            }
            rs2.close();
            statement2.close();
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
                Statement statement2 = co2.createStatement();
                String query = "SELECT id, name, price FROM stones;";
                String query2 = "SELECT id, name, price FROM torces;";
                ResultSet rs = statement.executeQuery(query);
                ResultSet rs2 = statement2.executeQuery(query2);
                listView = stoneList;
                listView2 = torcList;
                while (rs.next()) {
                    String sname = rs.getString("name");
                    int sprice = rs.getInt("price");
                    if (listView.getSelectionModel().getSelectedItem().equals(sname)) {
                        int a;
                        int b;
                        int c;
                        int d;
                        int p;
                        int obkant;
                        int obkantP;
                        a = Integer.parseInt(stoneWidth.getText());
                        b = Integer.parseInt(stoneHight.getText());
                        d = Integer.parseInt(stoneHowMany.getText());
                        c = a*b;
                        obkant = (a+b) * d;
                        obkantP = (int) (obkant * 3.5);
                        obkantPrice.setText(Integer.toString(obkantP));
                        p = (c * d)*sprice;
                        setter(obkant,p,obkantP);
                        //stonePrice.setText(Integer.toString(p));
                    }
                }
                while(rs2.next()){
                    String tname = rs2.getString("name");
                    int tprice = rs2.getInt("price");
                    if(listView2.getSelectionModel().getSelectedItem().equals(tname)) {
                        int tprisef = p1 * tprice;
                        torcPrice.setText(Integer.toString(tprisef));
                        int finaleprice = price + finaleobkant + tprisef;
                        stonePrice.setText(Integer.toString(finaleprice));
                    }
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
    void setter(int obkant, int price, int finaleobkant){
        p1 = obkant;
        this.price = price;
        this.finaleobkant = finaleobkant;
    }
}
