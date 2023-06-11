package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TalebOlusturController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_hukumlu_id;

    @FXML
    private AnchorPane talebOlustur_form;
    
    @FXML
    private Label lbl_ziyaretci_id;

    private static int ziyaretci_id;
    private static int hukumlu_id;
    
	public static int getZiyaretci_id() {
		return ziyaretci_id;
	}

	public static void setZiyaretci_id(int ziyaretci_id) {
		TalebOlusturController.ziyaretci_id = ziyaretci_id;
	}

	public static int getHukumlu_id() {
		return hukumlu_id;
	}

	public static void setHukumlu_id(int hukumlu_id) {
		TalebOlusturController.hukumlu_id = hukumlu_id;
	}

	@FXML
    void initialize() {
		lbl_hukumlu_id.setText(String.valueOf(getHukumlu_id()));
		lbl_ziyaretci_id.setText(String.valueOf(getZiyaretci_id()));
	}

}
