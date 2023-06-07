package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_back;

    @FXML
    private AnchorPane dashboard;

    @FXML
    void btn_back_Click(ActionEvent event) {
    	Window win = new Window();
		win.closeWindow(dashboard);
		win.openWindow("login");
    }

    @FXML
    void initialize() {}

}
