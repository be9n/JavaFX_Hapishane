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
    private Button btn_1;

    @FXML
    private Button btn_logout;

    @FXML
    private AnchorPane kullaniciPaneli;

    @FXML
    private AnchorPane kul_paneli_window;
    
    @FXML
    void btn_1_Click(ActionEvent event) {
    	Window.inSwitch(kul_paneli_window, "talebOlustur");
    }

    @FXML
    void btn_logout_Click(ActionEvent event) {
    	Window.logout(kullaniciPaneli);
    }

    @FXML
    void initialize() {
        assert btn_1 != null : "fx:id=\"btn_1\" was not injected: check your FXML file 'kullaniciPaneli.fxml'.";
        assert btn_logout != null : "fx:id=\"btn_logout\" was not injected: check your FXML file 'kullaniciPaneli.fxml'.";
        assert kullaniciPaneli != null : "fx:id=\"kullaniciPaneli\" was not injected: check your FXML file 'kullaniciPaneli.fxml'.";

    }

}

