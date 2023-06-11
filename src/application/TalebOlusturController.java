package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TalebOlusturController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_1;

    public int userId = LoginController.getUserSession();
    
    @FXML
    void initialize() {
        lbl_1.setText(String.valueOf(userId));
    }

}
