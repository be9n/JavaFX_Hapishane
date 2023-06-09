package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DashboardController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_hukumluler;
    
    @FXML
    private Button btn_kullanicilar;
    
    @FXML
    private Button btn_logout;

    @FXML
    private AnchorPane dashboard;
    
    @FXML
    private AnchorPane dsh_window;
    
    @FXML
    void btn_logout_Click(ActionEvent event) {
    	Window.logout(dashboard);
    }
    
    @FXML
    void btn_hukumluler_Click(ActionEvent event) {
    	Window.inSwitch(dsh_window, "hukumlulerTablo");
    }
    
    @FXML
    void btn_kullanicilar_Click(ActionEvent event) {
    	Window.inSwitch(dsh_window, "usersTablo");
    }

    @FXML
    void initialize() {}

}
