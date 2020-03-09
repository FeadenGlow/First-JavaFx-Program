package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ResultController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text stoneName;

    @FXML
    private Text stoneWidth;

    @FXML
    private Text stoneHight;

    @FXML
    private Text stonePrice;

    @FXML
    private Button buttonNext;

    @FXML
    void initialize() {
    }
}

