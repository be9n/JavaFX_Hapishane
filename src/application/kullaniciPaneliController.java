package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class kullaniciPaneliController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_hukumlu_sorgula;

    @FXML
    private Button btn_logout;
    
    @FXML
    private Button btn_taleplerim;

    @FXML
    private AnchorPane kullaniciPaneli;

    @FXML
    private AnchorPane kul_paneli_window;
    
    @FXML
    void btn_hukumlu_sorgula_Click(ActionEvent event) {
    	Window.inSwitch(kul_paneli_window, "hukumluSorgula");
    }

    @FXML
    void btn_logout_Click(ActionEvent event) {
    	Window.logout(kullaniciPaneli);
    }
    
    @FXML
    void btn_taleplerim_Click(ActionEvent event) {
    	Window.inSwitch(kul_paneli_window, "Taleplerim");
    }

    @FXML
    void initialize() {}

}

